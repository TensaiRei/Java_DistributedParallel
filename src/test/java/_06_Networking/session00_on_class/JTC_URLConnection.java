package _06_Networking.session00_on_class;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JTC_URLConnection {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void connectURL() throws IOException {
		String link = "https://www.sakura.fm/";
		URL url = URI.create(link).toURL();
		Scanner scanner = new Scanner(url.openStream());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
		scanner.close();
	}

}
