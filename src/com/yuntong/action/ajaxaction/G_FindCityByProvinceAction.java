/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.yuntong.action.ajaxaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yuntong.business.vo.Y_AreaInfoVO;
import com.yuntong.service.G_IAreaInfoService;
import com.yuntong.service.Y_IAreaManagerService;

/** 
 * MyEclipse Struts
 * Creation date: 04-09-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class G_FindCityByProvinceAction extends Action {
	private G_IAreaInfoService areaInfoService;

	public G_IAreaInfoService getAreaInfoService() {
		return areaInfoService;
	}

	public void setAreaInfoService(G_IAreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//得到省
		String str=request.getParameter("province");
		String province=new String(str.getBytes("ISO-8859-1"),"UTF-8");
		//根据省得到城市
		List list=areaInfoService.findCityByProvince(province);
		//封装城市为xml格式返回
		if(list.size()>0){
			out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.print("<info>");
			for (int i = 0; i < list.size(); i++) {
				Y_AreaInfoVO vo=(Y_AreaInfoVO) list.get(i);
				out.print("<city value='"+vo.getAreaCity()+"'>");
				out.print(vo.getAreaCity());
				out.print("</city>");
			}
			out.print("</info>");
		}else{
			out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.print("<info>");
			out.print("<city value='9999'>");
			out.print("---------");
			out.print("</city>");
			out.print("</info>");
		}
		out.close();
		return null;
	}
}