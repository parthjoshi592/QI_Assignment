import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	public static ExtentReports getInstance() {
		ExtentReports extent;
		String Path = "C:\\Users\\Parth\\eclipse-workspace\\extent-report-files\\report-demo.html";
		extent = new ExtentReports(Path, false);

		return extent;
	}
}
