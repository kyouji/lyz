package com.ynyes.lyz.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.lyz.entity.TdDeliveryType;
import com.ynyes.lyz.entity.TdDiySite;
import com.ynyes.lyz.entity.TdPayType;
import com.ynyes.lyz.entity.TdPriceList;
import com.ynyes.lyz.entity.TdShippingAddress;
import com.ynyes.lyz.service.TdDiySiteService;
import com.ynyes.lyz.service.TdManagerLogService;
import com.ynyes.lyz.service.TdPriceListItemService;
import com.ynyes.lyz.service.TdPriceListService;
import com.ynyes.lyz.util.SiteMagConstant;

// 价目表管理

@Controller
@RequestMapping(value="/Verwalter/pricelist")
public class TdManagerPriceListController {
	@Autowired
	TdPriceListService tdPriceListService;
	
	@Autowired
	TdPriceListItemService tdPriceListItemService;
	
	@Autowired
	TdDiySiteService tdDiySiteService;
	
	@Autowired
	TdManagerLogService tdManagerLogService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String pricelist( String __EVENTTARGET,Integer page,   Integer size, 
			          String __EVENTARGUMENT,
			          String __VIEWSTATE,
			          String keywords,
			          Long[] listId,
			          Integer[] listChkId,
			          Long[] listSortId,
			          ModelMap map,
			          HttpServletRequest req){
		 String username = (String) req.getSession().getAttribute("manager");
	     if (null == username) {
	         return "redirect:/Verwalter/login";
	     }
	        
	     if (null == page || page < 0)
	     {
	         page = 0;
	     }
	        
	     if (null == size || size <= 0)
	     {
	         size = SiteMagConstant.pageSize;;
	     }
	        
	     if (null != keywords)
	     {
	         keywords = keywords.trim();
	     }
	     
	     if (null != __EVENTTARGET)
	        {
	            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
	            {
	                btnDelete("pricelist", listId, listChkId);
	                	               
	                tdManagerLogService.addLog("delete", "删除价目表", req);
	               
	            }
	            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
	            {
	                btnSave("pricelist", listId, listSortId);

	                tdManagerLogService.addLog("edit", "修改价目表", req);
	               
	            }
	            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
	            {
	                if (null != __EVENTARGUMENT)
	                {
	                    page = Integer.parseInt(__EVENTARGUMENT);
	                } 
	            }
	        } 
	     
	     map.addAttribute("page", page);
	     map.addAttribute("size", size);
	     map.addAttribute("keywords", keywords);
	     map.addAttribute("__EVENTTARGET", __EVENTTARGET);
	     map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
	     map.addAttribute("__VIEWSTATE", __VIEWSTATE);
	     
	     if (null == keywords)
         {
             map.addAttribute("pricelist_page", 
                     tdPriceListService.findAll(page, size));
         }
         else
         {
             map.addAttribute("pricelist_page", 
            		 tdPriceListService.searchAll(keywords, page, size));
         }
         
         return "/site_mag/pricelist_list";
	}
	
	@RequestMapping(value = "/edit")
    public String categoryEditDialog(Long id, String __EVENTTARGET,
            String __EVENTARGUMENT, String __VIEWSTATE, ModelMap map,
            HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }

        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        // 参数类型表

       
        if (null != id) {
            map.addAttribute("pricelist", tdPriceListService.findOne(id));
        }
        

        return "/site_mag/pricelist_edit";

    }
	
	 private void btnSave(String type, Long[] ids, Long[] sortIds)
	    {
	        if (null == type || type.isEmpty())
	        {
	            return;
	        }
	        
	        if (null == ids || null == sortIds
	                || ids.length < 1 || sortIds.length < 1)
	        {
	            return;
	        }
	        
	        for (int i = 0; i < ids.length; i++)
	        {
	            Long id = ids[i];
	            
	            if (type.equalsIgnoreCase("pricelist"))
	            {
	                TdPriceList e = tdPriceListService.findOne(id);
	                
	                if (null != e)
	                {
	                    if (sortIds.length > i)
	                    {
	                        e.setSortId(sortIds[i]);
	                        tdPriceListService.save(e);
	                    }
	                }
	            }
	           
	        }
	    }
	    
	    private void btnDelete(String type, Long[] ids, Integer[] chkIds)
	    {
	        if (null == type || type.isEmpty())
	        {
	            return;
	        }
	        
	        if (null == ids || null == chkIds
	                || ids.length < 1 || chkIds.length < 1)
	        {
	            return;
	        }
	        
	        for (int chkId : chkIds)
	        {
	            if (chkId >=0 && ids.length > chkId)
	            {
	                Long id = ids[chkId];
	                
	                if (type.equalsIgnoreCase("pricelist"))
	                {
	                	tdPriceListService.delete(id);
	                }
	                
	            }
	        }
	    }
}
