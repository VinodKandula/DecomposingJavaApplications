package dja.housecleaning.client;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

import dja.housecleaning.front.OrderForm;
import dja.housecleaning.front.Ronald;
import other.things.CleaningTool;
import other.things.Van;

public class Client {

	public static void main(String[] args) throws IOException {
		new Client();
	}

	private Client() throws IOException {
		
		Terminal terminal = TerminalBuilder.terminal();
		StringsCompleter completer = new StringsCompleter("exit", "clean", "borrowVan", "borrowTools");
		
		LineReader reader = LineReaderBuilder.builder()
				.terminal(terminal)
				.completer(completer)
				.build();

		String javaVersion = new AttributedStringBuilder()
			.style(AttributedStyle.DEFAULT.foreground(AttributedStyle.RED))
			.append(System.getProperty("java.version"))
			.toAnsi();
		System.out.println("Java version is: " + javaVersion);

		Ronald ronald = Ronald.PERSON;
		String line;
		while (true) {
			line = reader.readLine("house cleaning > ").trim();

			if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                break;
        	}
			ParsedLine parsedLine = reader.getParser().parse(line, 0);
			String command = parsedLine.words().get(0);
			String param = parsedLine.words().size() > 1 ? parsedLine.words().get(1) : null;
			
            try {
            	switch (command) {
					case "clean":
						OrderForm orderForm = ronald.getOrderForm();
						orderForm.setAddress("some street");
						orderForm.setInstructions("clean the floor");
						ronald.cleanMyHousePlease(orderForm, 200);
						break;
					case "borrowVan":
						Method borrowVan = ronald.getClass().getMethod("borrowVan", String.class);
						Van van = (Van)borrowVan.invoke(ronald, param);
						van.drive("⛱️");
						System.out.println("Client: I'm on vacation! Will be back next week! May be!");
						break;
					case "borrowTools":
						Method borrowTools = ronald.getClass().getMethod("borrowTools", String.class);
						List<CleaningTool> cleaningTools = (List<CleaningTool>)borrowTools.invoke(ronald, param);
						cleaningTools.clear();
						System.out.println("Client: Opps I lost your 🛠️. I'm so sorry!");
						break;
					default:
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
