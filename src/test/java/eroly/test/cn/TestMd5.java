package eroly.test.cn;

import com.eroly.util.DigestUtil;
import com.eroly.util.MD5_Encoding;

public class TestMd5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MD5_Encoding encoding = new MD5_Encoding();
		String md5ofStr = encoding.getMD5ofStr("abc");
		System.out.println(md5ofStr);
		System.out.println(DigestUtil.hmacSign("abc", ""));
		System.out.println("39053A09C00BC4C573A90319E10D7162".length());
	}

}
