<?php
	//lolz just in case
	ini_set("memory_limit","3G");

	$path = './';

	$handle = fopen(
		$path . 'A-large-practice.in',
		'r');

	$out = fopen(
		$path . 'A-large-practice.out',
		'w');

	$test = array();

	if ($handle) {
		$testCases = fgets($handle);
		while (feof($handle) === false) {
			$test[] = fgets($handle);
		}
		fclose($handle);
	} else {
		echo 'error opening file';
	}

	function intify($n) {
		return intval($n);
	}
	
	$start = microtime(true);

	$memoPad = array();
	for ($n = 2; $n <= 10; $n++) {
		$memoPad[$n] = array();
	}

	$subsets = array();
	for ($t = 0; $t < $testCases; $t++) {
		$caseStart = microtime(true);
		$tNum = $t + 1;

		if (empty($test[$t])) {
			continue;
		}
		$bases = explode(' ', $test[$t]);
		$bases = array_map("intify", $bases);
	
		$found = false;
		$numOfBases = count($bases);
		$largestBase = $bases[count($bases) - 1];
		$smallestBase = $bases[0];

		// find the largest value in which all of the digits of the key are in the current test
		$startPos = 1;
		foreach ($subsets as $key => $value) {
			$isSubset = true;
			
			$baseArray = explode(" ", $key);
			foreach ($baseArray as $base) {

				if(!in_array($base, $bases)) {
					$isSubset = false;
					break;
				}
			}
			if ($isSubset) {
				$startPos = max($startPos, $value - 1);
			}
		}

		// 1) Take the largest base and find the smallest magic number.
		// 2) See if it is also magic in the other bases
		// 3) If yes then done
		// 4) If not then find the next smallest magic number in the largest base
		$nextSmallestMN = nextMN($largestBase, $startPos, $memoPad);
		while ($found === false) {

			$validForAll = true;
			for ($j = 0; $j < $numOfBases - 1; $j++) {
				$validForAll = $validForAll && isMagic($bases[$j], $nextSmallestMN, $memoPad);
				if ($validForAll === false) {
					break;
				}
				$memoPad[$bases[$j]][$nextSmallestMN] = true;
			}
			if ($validForAll === true) {
				$found = true;
				$subsets[implode(' ', $bases)] = $nextSmallestMN;
			} else {
				$nextSmallestMN = nextMN($largestBase, $nextSmallestMN, $memoPad);
			}
		}
		$caseEnd = (microtime(true) - $caseStart);
		if ($caseEnd > 1) {
			echo "case $tNum: $caseEnd \n";
		}
		fwrite($out, "Case #{$tNum}: {$nextSmallestMN}\n");
	}
	$end = microtime(true) - $start;
	echo 'end ' . $end;
	function nextMN($base, $num, &$memoPad) {
		$num = $num + 1;
		while(!isMagic($base, $num, $memoPad)) {
			$memoPad[$base][$num] = false;
			$num++;
		}
		$memoPad[$base][$num] = true;
		return $num;
	}
	function isMagic($base, $num, &$memoPad) {
		if (isset($memoPad[$base][$num])) {
			return $memoPad[$base][$num];
		}
		$visitedNums = array();
		// Record the number
		// Reduce the number to its squared thing
		// Continue until the number has already been reached or it reduces to 1
		
		while( !in_array($num, $visitedNums) ) {
			$visitedNums[] = $num;
			$num = reduce($base, $num);
			if ($num == 1) {
				foreach ($visitedNums as $key) {
					$memoPad[$base][$key] = true;
				}
				return true;
			} else if (isset($memoPad[$base][$num])) {
				return $memoPad[$base][$num];
			}
		}
		foreach ($visitedNums as $key) {
			$memoPad[$base][$key] = false;
		}
		return false;

	}
	function addToAllToPad($base, $visitedNums, &$memoPad, $magicValue) {
		foreach ($visitedNums as $key) {
			$memoPad[$base][$key] = $magicValue;
		}
	}
	//Returns the calc i.e. 81(B10) = 65(B10)
	function reduce($base, $num) {

		// 1) Extract the digits
		// 2) Square the digits
		// 3) Add the digits
		$num = base_convert($num, 10, $base);
		$digits = str_split($num);

		$digCount = count($digits);
		for ($i = 0; $i < $digCount; $i++) {
			$n = $digits[$i];
			$n = $n * $n;
			$digits[$i] = $n;
		}
		$digSums = array_sum($digits);
		return $digSums;

	}


?>