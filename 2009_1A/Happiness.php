<?php
	//running php from a vagrant box which is symlinked to pcPath 
	$pcPath = 'C:\\Users\\RasPat\\Dev\\GCodeJam\\2009_1A\\';
	$vagrantPath = './';
	$path = $vagrantPath;

	$handle = fopen(
		$path . 'A-small-practice.in',
		'r');

	$out = fopen(
		$path . 'A-small-practice.out',
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
	
	$memoPad = array();
	for ($n = 2; $n <= 10; $n++) {
		$memoPad[$n] = array();
	}

	// echo isMagic(10, 82) ? 'true' : 'false';
	// $nextB2 = nextMN(9, 6, $memoPad);
	// print_r($nextB2);
	// exit;
	$start = microtime(true);
	for ($t = 0; $t < $testCases; $t++) {
		echo $t;
		if (empty($test[$t])) {
			continue;
		}
		$bases = explode(' ', $test[$t]);
		$bases = array_map("intify", $bases);
	
		$found = false;
		$numOfBases = count($bases);
		$largestBase = $bases[count($bases) - 1];

		// 1) Take the largest base and find the smallest magic number.
		// 2) See if it is also magic in the other bases
		// 3) If yes then done
		// 4) If not then find the next smallest magic number in the largest base
		

		$nextSmallestMN = nextMN($largestBase, 2, $memoPad);
		while ($found === false) {

			$validForAll = true;
			for ($j = 0; $j < $numOfBases - 1; $j++) {
				$validForAll = $validForAll && isMagic($bases[$j], $nextSmallestMN, $memoPad);
				if ($validForAll === false) {
					break;
				}
				$memoPad[$bases[$j]][] = $nextSmallestMN;
			}
			if ($validForAll === true) {
				$found = true;
			} else {
				$nextSmallestMN = nextMN($largestBase, $nextSmallestMN, $memoPad);
			}
		}
		$tNum = $t + 1;
		fwrite($out, "Case #{$tNum}: {$nextSmallestMN}\n");
	}
	$end = microtime(true) - $start;
	echo 'end ' . $end;
	function nextMN($base, $num, &$memoPad) {
		$num = $num + 1;
		while(!isMagic($base, $num, $memoPad)) {
			$num++;
		}
		array_push($memoPad[$base], $num);
		// print_r($memoPad);
		// exit;
		return $num;
	}
	function isMagic($base, $num, $memoPad) {
		if (in_array($num, $memoPad[$base])) {
			echo 'found ';
			return true;
		}
		$visitedNums = array();
		// Record the number
		// Reduce the number to its squared thing
		// Continue until the number has already been reached or it reduces to 1
		
		// $visitedNums[] = $num;
		// $nextNum = reduce($base, $num);
		while( !in_array($num, $visitedNums) ) {
			$visitedNums[] = $num;
			$num = reduce($base, $num);
			if ($num == 1) {
				return true;
			}
		}
		return false;

	}

	//Returns the calc i.e. 81(B10) = 65(B10)
	function reduce($base, $num) {

		// 1) Extract the digits
		// 2) Square the digits
		// 3) Add the digits
		$num = base_convert($num, 10, $base);
		$digits = array();
		while ($num > 0) {
			$digits[] = $num % 10;
			$num = $num / 10;
			$num = intval($num);
		}
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