package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@Autowired
	private HelloService helloService;

	/**
	 * GET用の処理.
	 */
	@GetMapping("/hello")
	public String getHello() {
		// hello.htmlに画面遷移
		return "hello";
	}

	/**
	 * POST用の処理.
	 */
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1") String str, Model model) {

		// 画面から受け取った文字列をModelに登録
		model.addAttribute("sample", str);

		// helloResponse.htmlに画面遷移
		return "helloResponse";
	}

	/**
	 * POST用の処理（DB）.
	 */
	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("text2") String str, Model model) {

		// Stringからint型に変換
		int id = Integer.parseInt(str);

		// １件検索
		Employee employee = helloService.findOne(id);

		// 検索結果をModelに登録
		model.addAttribute("id", (employee).getEmployeeId());
		model.addAttribute("name", employee.getEmployeeName());
		model.addAttribute("age", employee.getAge());

		// helloResponseDB.htmlに画面遷移
		return "helloResponseDB";
	}
}
