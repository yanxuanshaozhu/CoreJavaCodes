package pers.yanxuanshaozhu.corejavach15.ch15_parallel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStreams {
	public static void main(String[] args) throws IOException {
		String contents = new String(
				Files.readAllBytes(Paths.get("./src/pers/yanxuanshaozhu/corejavach15/ch15_parallel/alice30.txt")),
				StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("\\PL+"));

		int[] shortWrods = new int[10];
		wordList.parallelStream().forEach(s -> {
			if (s.length() < 10) {
				shortWrods[s.length()]++;
			}
		});
		System.out.println(Arrays.toString(shortWrods));

		Arrays.fill(shortWrods, 0);
		wordList.parallelStream().forEach(s -> {
			if (s.length() < 10) {
				shortWrods[s.length()]++;
			}
		});
		System.out.println(Arrays.toString(shortWrods));

		Map<Integer, Long> shortWordCounts = wordList.parallelStream().filter(s -> s.length() < 10)
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(shortWordCounts);

		Map<Integer, List<String>> result = wordList.parallelStream().collect(Collectors.groupingBy(String::length));
		System.out.println(result.get(14));

		result = wordList.parallelStream().collect(Collectors.groupingBy(String::length));
		System.out.println(result.get(14));

		Map<Integer, Long> wordCounts = wordList.parallelStream()
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(wordCounts);
	}
}
