package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_DataType {

    //2 nhóm kiểu dữ liệu

    //Access modifier: Public/Default/Protected/Private
    // Nhóm 1 dữ liệu nguyên thủy

    //char: kí tự (character)
    //khi khởi tạo giá trị nằm trong nháy '
    //byte/short/ int/ long: số nguyên
    // float/double : số thực
    // boolean : logic

    // Nhóm 2 dữ liệu tham chiếu
    //String : kiểu chuỗi => khi khởi tạo nằm trong nháy đơn ,có nhiều ký tự
    // convention : tên hàm/tên biến : viết dưới dạng camnel case
    // chữ cái đầu tiên viết thường.

    //Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    //Interface
    WebDriver webDriver;
    //Array
    //List/Set/Queue
    //Map
    //Object

    //=> tham chiều là khi 2 giá trị gắn bằng nhau thì khi set giá trị nó sẽ thay đổi theo còn nguyền thủy thì ko
}
