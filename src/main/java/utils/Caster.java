package utils;

import model.*;
import java.util.*;

public class Caster {
	public static List<CustomAsyncPage> castToAsync(List<BasePage> pages) {
		List<CustomAsyncPage> list = new ArrayList<CustomAsyncPage>();
		for (BasePage page : pages) {
			list.add(new CustomAsyncPage(page.url, page.word, page.refreshTime));
		}
		return list;
	}

	public static CustomPage castToCustom(BasePage page) {
		return new CustomPage(page.url, page.word, page.refreshTime);
	}
}
