package htmx.app.utils;

import htmx.app.utils.Utils.DateUtils;
import java.io.Serializable;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
	@Test
	public void dateUtilsFormatDate() {
		String date = "01-01-2023";
		LocalDate expected = LocalDate.of(2023, 1, 1);
		LocalDate actual = DateUtils.formatDate(date);

		assertEquals(expected, actual);
	}
	@Test
	void formatDate_withYearFirstFormat_returnsCorrectFormat() {
		String inputDate = "2022-03-01";
		LocalDate expectedDate = LocalDate.of(2022, 3, 1);
		assertEquals(expectedDate, Utils.DateUtils.formatDate(inputDate));
	}

	@Test
	void formatDate_withDayFirstFormat_returnsCorrectFormat() {
		String inputDate = "01-03-2022";
		LocalDate expectedDate = LocalDate.of(2022, 3, 1);
		assertEquals(expectedDate, Utils.DateUtils.formatDate(inputDate));
	}

	@Test
	void formatDate_withSlashSeparator_returnsCorrectFormat() {
		String inputDate = "01/03/2022";
		LocalDate expectedDate = LocalDate.of(2022, 3, 1);
		assertEquals(expectedDate, Utils.DateUtils.formatDate(inputDate));
	}

	@Test
	void formatDate_withContinuousNumbers_returnsCorrectFormat() {
		String inputDate = "20220301";
		LocalDate expectedDate = LocalDate.of(2022, 3, 1);
		assertEquals(expectedDate, Utils.DateUtils.formatDate(inputDate));
	}

	@Test
	void formatDate_withInvalidFormat_throwsIllegalArgumentException() {
		String inputDate = "01-2022-03";
		assertThrows(IllegalArgumentException.class, () -> Utils.DateUtils.formatDate(inputDate));
	}
}
