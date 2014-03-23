<?php
	//running php from a vagrant box which is symlinked to pcPath 
	$pcPath = 'C:\\Users\\RasPat\\Dev\\GCodeJam\\2009_1A\\';
	$vagrantPath = './';
	$path = $vagrantPath;

	$handle = fopen(
		$path . 'A-large-practice.in',
		'r');

	$out = fopen(
		$path . 'A-large-practice.out',
		'w');
	$scratchPad = fopen(
		$path . 'scrachPad.txt', 'w');
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
	
	$memoPad = array();
	for ($n = 2; $n <= 10; $n++) {
		$memoPad[$n] = array();
	}

	$start = microtime(true);
	for ($t = 0; $t < $testCases; $t++) {
		$caseStart = microtime(true);
		$tNum = $t + 1;

		echo "case: $tNum";
		if (empty($test[$t])) {
			continue;
		}
		$bases = explode(' ', $test[$t]);
		$bases = array_map("intify", $bases);
	
		$found = false;
		$numOfBases = count($bases);
		$largestBase = $bases[count($bases) - 1];
		$smallestBase = $bases[0];
		// 1) Take the largest base and find the smallest magic number.
		// 2) See if it is also magic in the other bases
		// 3) If yes then done
		// 4) If not then find the next smallest magic number in the largest base
		$nextSmallestMN = nextMN($smallestBase, 2, $memoPad);
		while ($found === false) {

			$validForAll = true;
			for ($j = 1; $j < $numOfBases; $j++) {
				$validForAll = $validForAll && isMagic($bases[$j], $nextSmallestMN, $memoPad);
				if ($validForAll === false) {
					break;
				}
				$memoPad[$bases[$j]][$nextSmallestMN] = true;
			}
			if ($validForAll === true) {
				$found = true;
			} else {
				$nextSmallestMN = nextMN($smallestBase, $nextSmallestMN, $memoPad);
			}
		}
		$caseEnd = microtime(true) - $caseStart;
		print_r("");
		echo ' caseEnd: ' . $caseEnd . "\n";
		// if ($tNum === 3) {
		// 	foreach ($memoPad as $base => $table) {
		// 		fwrite($scratchPad, $base);
		// 		foreach ($table as $key => $value) {
		// 			fwrite($scratchPad, "\t$key => $value\n");
		// 		}
		// 		fwrite($scratchPad, "\n");
		// 	}
		// 	// fwrite($scratchPad, implode(" ", implode("\n ", $memoPad)));
		// 	// print_r(expression);
		// 	print_r($memoPad);
		// 	echo isset($memoPad[2][215]) ? 'set' : 'notset';
		// 	exit;
		// }
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
		// while ($num > 0.01) {
		// 	$digits[] = $num % 10;
		// 	$num = $num / 10;
		// 	// $num = intval($num);
		// }
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