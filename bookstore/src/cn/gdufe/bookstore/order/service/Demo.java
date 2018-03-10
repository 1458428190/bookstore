package cn.gdufe.bookstore.order.service;

import org.junit.Test;

public class Demo {
	@Test
	public void fun1()
	{
		/**
		 * https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MedId=10001126856&p
		 * 2_Order=EABF3FBB67F7407BA522C13A676DB3C0&p3_Amt=0.01&p4_Cur
		 * =CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/bookStore/OrderServlet?
		 * method=back&p9_SAF=&pa_MP=&pd_FrpId=BOCO-NET-B2C&pr_NeedResponse=1&hmac=3eb97750c829530b816a980244dea742
		 */
		 String hmac = PaymentUtil.buildHmac("Buy", "10001126856", "123456",
				"0.01", "CNY","" , "", "", "http://localhost:8080/bookstore/OrderServlet?method=back", "", "", 
				"ICBC-NET-B2C", "1", "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
		 System.out.println(hmac);
	}
	/*
	 
	 //https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MedId=10000326625&p2_Order=EABF3FBB67F7407BA522C13A676DB3C0&p3_Amt=0.01&p4_Cur=CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/bookStore/OrderServlet?method=back&p9_SAF=&pa_MP=&pd_FrpId=BOCO-NET-B2C&pr_NeedResponse=1&hmac=60e65d7152c0daf0838513111e915dce
	
	 
	 
	 https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MerId=10001126856&p2_Order=123456&p3_Amt=0.01&p4_Cur=CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/bookstore/OrderServlet?method=back&p9_SAF=&pa_MP=&pd_FrpId=ICBC-NET-B2C&pr_NeedResponse=1&hmac=41da9fb0fdc46b6a60aa9e53b5ff5c68
	 */
}
