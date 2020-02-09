package pers.yanxuanshaozhu.corejavach15.ch15_streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CoungLongWords {
	public static void main(String[] args) throws IOException {
		// The relative path starts from the root directory of a project in Eclipse
		String countents = new String(
				Files.readAllBytes(Paths.get("./src/pers/yanxuanshaozhu/corejavach15/ch15_streams/IHaveADream.txt")),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(countents.split(" "));

		long count = 0;
		for (String word : words) {
			if (word.length() > 4) {
				count++;
			}
		}
		System.out.println(count);

		count = words.stream().filter(w -> w.length() > 4).count();
		System.out.println(count);

		count = words.parallelStream().filter(w -> w.length() > 4).count();
		System.out.println(count);

	}
}
