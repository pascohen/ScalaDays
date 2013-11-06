package test.pcohen.scaladays.demo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

class Response {

	private final String output;
	private final String compilationStatus;

	public Response(String output, String compilationStatus) {
		this.output = output;
		this.compilationStatus = compilationStatus;
	}

	public String getOutput() {
		return output;
	}

	public String getCompilationStatus() {
		return compilationStatus;
	}
}

class Request {

	private String content;

	public Request() {
	}

	public void setContent(String c) {
		content = c;
	}

	public String getContent() {
		return content;
	}
}

@Controller
@RequestMapping("/eval")
public class EvalController {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Response evalCode(@RequestBody String content) throws Exception {
		Gson gson = new Gson();
		Request request = gson.fromJson(content, Request.class);
		String encoding = "UTF-8";
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(stream, true, encoding);
		PrintStream oldPrintStream = System.out;
		System.setOut(printStream);
		PrintWriter writer = new PrintWriter(printStream);

		try {
			
		Object s = new Eval().eval(request.getContent(),writer);
		System.setOut(oldPrintStream);
		return new Response(stream.toString(), s.toString());
		} catch (Exception e) {
			return new Response(stream.toString(), e.toString());
		}
	}
}
