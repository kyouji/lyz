package com.ynyes.lyz.controller.management;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.lyz.entity.TdGoods;
import com.ynyes.lyz.entity.TdPriceChangeLog;
import com.ynyes.lyz.entity.TdProductCategory;
import com.ynyes.lyz.service.TdArticleService;
import com.ynyes.lyz.service.TdGoodsService;
import com.ynyes.lyz.service.TdManagerLogService;
import com.ynyes.lyz.service.TdPriceChangeLogService;
import com.ynyes.lyz.service.TdProductCategoryService;
import com.ynyes.lyz.service.TdProductService;
import com.ynyes.lyz.util.SiteMagConstant;


/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/goods")
public class TdManagerGoodsController {
    
    @Autowired
    TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdGoodsService tdGoodsService;
    
//    @Autowired
//    TdWarehouseService tdWarehouseService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
//    @Autowired
//    TdBrandService tdBrandService;
    
//    @Autowired
//    TdParameterService tdParameterService;
    
    @Autowired
    TdProductService tdProductService;
    
    @Autowired
    TdPriceChangeLogService tdPriceChangeLogService;
    
    @RequestMapping(value="/edit/parameter/{categoryId}", method = RequestMethod.POST)
    public String parameter(@PathVariable Long categoryId, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        TdProductCategory tpc = tdProductCategoryService.findOne(categoryId);
        
        if (null != tpc)
        {
            Long pcId = tpc.getParamCategoryId();
            
            if (null != pcId)
            {
//                map.addAttribute("param_list", tdParameterService.findByCategoryTreeContaining(pcId));
                
                // 查找产品列表
                map.addAttribute("product_list", tdProductService.findByProductCategoryTreeContaining(categoryId));
            
                // 查找品牌
//                map.addAttribute("brand_list", tdBrandService.findByProductCategoryTreeContaining(categoryId));
            }
           
        }
        
        return "/site_mag/goods_category_param_list";
    }
    
    @RequestMapping(value="/price", method = RequestMethod.GET)
    public String chgPrice(Long goodsId, ModelMap map, HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != goodsId)
        {
            TdGoods goods = tdGoodsService.findOne(goodsId);
            
            map.addAttribute("goods", goods);
        }
        
        return "/site_mag/dialog_goods_change_price";
    }
    
    @RequestMapping(value="/price/log", method = RequestMethod.GET)
    public String chgPriceLog(Long goodsId, 
                        Integer page, 
                        Integer size, 
                        String __EVENTTARGET,
                        String __EVENTARGUMENT,
                        String __VIEWSTATE,
                        ModelMap map, 
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != __EVENTTARGET)
        {
            switch (__EVENTTARGET)
            {
            case "btnPage":
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
                break;
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size < 0)
        {
            size = SiteMagConstant.pageSize;
        }
        
        if (null != goodsId)
        {
            Page<TdPriceChangeLog> logPage = tdPriceChangeLogService.findByGoodsIdOrderByIdDesc(goodsId, page, size);
            
            map.addAttribute("price_log_page", logPage);
            map.addAttribute("goodsId", goodsId);
        }

        map.addAttribute("page", page);
        map.addAttribute("size", size);
        
        return "/site_mag/dialog_goods_price_log";
    }
    
    @RequestMapping(value="/price/set", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> setPrice(Long goodsId, Double outPrice, ModelMap map, HttpServletRequest req){
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            res.put("message", "请重新登录");
            return res;
        }
        
        if (null == goodsId)
        {
            res.put("message", "商品ID不存在");
            return res;
        }
        
        if (null == outPrice)
        {
            res.put("message", "价格不存在");
            return res;
        }
        
        TdGoods goods = tdGoodsService.findOne(goodsId);
        
        goods.setOutFactoryPrice(outPrice);
        
        goods = tdGoodsService.save(goods, username);
        
        tdManagerLogService.addLog("edit", "用户修改商品价格：" + goods.getTitle(), req);
        
        res.put("code", 0);
        
        return res;
    }
    
    @RequestMapping(value="/list")
    public String goodsList(Integer page, 
                              Integer size,
                              Long categoryId,
                              String property,
                              String __EVENTTARGET,
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
            switch (__EVENTTARGET)
            {
            case "lbtnViewTxt":
            case "lbtnViewImg":
                __VIEWSTATE = __EVENTTARGET;
                break;
            
            case "btnSave":
                btnSave(listId, listSortId, username);
                tdManagerLogService.addLog("edit", "用户修改商品", req);
                break;
                
            case "btnDelete":
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "用户删除商品", req);
                break;
                
            case "btnPage":
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
                break;
            
            case "btnOnSale":
                if (null != __EVENTARGUMENT)
                {
                    Long goodsId = Long.parseLong(__EVENTARGUMENT);
                    
                    if (null != goodsId)
                    {
                        TdGoods goods = tdGoodsService.findOne(goodsId);
                        
                        if (null != goods)
                        {
                            if (null == goods.getIsOnSale() || !goods.getIsOnSale())
                            {
                                goods.setIsOnSale(true);
                                tdManagerLogService.addLog("delete", "用户上架商品:" + goods.getTitle(), req);
                            }
                            else
                            {
                                goods.setIsOnSale(false);
                                tdManagerLogService.addLog("delete", "用户下架商品:" + goods.getTitle(), req);
                            }
                            tdGoodsService.save(goods, username);
                        }
                    }
                } 
                break;
            }
        }
        
        if (null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewTxt")
                || null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewImg"))
        {
            __VIEWSTATE = __EVENTTARGET;
        }
        
        map.addAttribute("category_list", tdProductCategoryService.findAll());
            
        Page<TdGoods> goodsPage = null;
        
        if (null == categoryId)
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                goodsPage = tdGoodsService.findAllOrderBySortIdAsc(page, size);
            }
            else
            {
                goodsPage = tdGoodsService.searchAndOrderBySortIdAsc(keywords, page, size);
            }
        }
        else
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                goodsPage = tdGoodsService.findByCategoryIdTreeContainingOrderBySortIdAsc(categoryId, page, size);
            }
            else
            {
                goodsPage = tdGoodsService.searchAndFindByCategoryIdOrderBySortIdAsc(keywords, categoryId, page, size);
            }
        }
        
        map.addAttribute("content_page", goodsPage);
        
        // 参数注回
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("categoryId", categoryId);
        map.addAttribute("property", property);
        
        // 文字列表模式
        if (null != __VIEWSTATE && __VIEWSTATE.equals("lbtnViewTxt"))
        {
            return "/site_mag/goods_txt_list";
        }
        
        // 图片列表模式
        return "/site_mag/goods_pic_list";
    }
    
    @RequestMapping(value="/list", method=RequestMethod.POST)
    public String goodsListPost(Integer page, 
                              Integer size,
                              Long categoryId,
                              String property,
                              String saleType,
                              String __EVENTTARGET,
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
            switch (__EVENTTARGET)
            {
            case "lbtnViewTxt":
            case "lbtnViewImg":
                __VIEWSTATE = __EVENTTARGET;
                break;
            
            case "btnSave":
                btnSave(listId, listSortId, username);
                tdManagerLogService.addLog("edit", "用户修改商品", req);
                break;
                
            case "btnDelete":
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "用户删除商品", req);
                break;
                
            case "btnPage":
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
                break;
                
            case "btnOnSale":
                if (null != __EVENTARGUMENT)
                {
                    Long goodsId = Long.parseLong(__EVENTARGUMENT);
                    
                    if (null != goodsId)
                    {
                        TdGoods goods = tdGoodsService.findOne(goodsId);
                        
                        if (null != goods)
                        {
                            if (null == goods.getIsOnSale() || !goods.getIsOnSale())
                            {
                                goods.setIsOnSale(true);
                                goods.setOnSaleTime(new Date());
                                tdManagerLogService.addLog("delete", "用户上架商品:" + goods.getTitle(), req);
                            }
                            else
                            {
                                goods.setIsOnSale(false);
                                tdManagerLogService.addLog("delete", "用户下架商品:" + goods.getTitle(), req);
                            }
                            tdGoodsService.save(goods, username);
                        }
                    }
                } 
                break;
            }
        }
        
        if (null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewTxt")
                || null != __EVENTTARGET && __EVENTTARGET.equalsIgnoreCase("lbtnViewImg"))
        {
            __VIEWSTATE = __EVENTTARGET;
        }
        
        map.addAttribute("category_list", tdProductCategoryService.findAll());
        
        Page<TdGoods> goodsPage = null;
        
//        if (null == categoryId)
//        {
//            if (null == property || property.isEmpty())
//            {
//                if (null == keywords || "".equalsIgnoreCase(keywords))
//                {
//                    goodsPage = tdGoodsService.findAllOrderBySortIdAsc(page, size);
//                }
//                else
//                {
//                    goodsPage = tdGoodsService.searchAndOrderBySortIdAsc(keywords, page, size);
//                }
//            }
//            else
//            {
//                if (property.equalsIgnoreCase("isOnSale"))
//                {
//                    if (null == keywords || "".equalsIgnoreCase(keywords))
//                    {
//                        goodsPage = tdGoodsService.findByIsOnSaleTrueOrderBySortIdAsc(page, size);
//                    }
//                    else
//                    {
//                        goodsPage = tdGoodsService.searchAndIsOnSaleTrueOrderBySortIdAsc(keywords, page, size);
//                    }
//                }
//            }
//        }
//        else
//        {
//            if (null == property || property.isEmpty())
//            {
//                if (null == keywords || "".equalsIgnoreCase(keywords))
//                {
//                    goodsPage = tdGoodsService.findByCategoryIdTreeContainingOrderBySortIdAsc(categoryId, page, size);
//                }
//                else
//                {
//                    goodsPage = tdGoodsService.searchAndFindByCategoryIdOrderBySortIdAsc(keywords, categoryId, page, size);
//                }
//            }
//            else
//            {
//                if (property.equalsIgnoreCase("isOnSale"))
//                {
//                    if (null == keywords || "".equalsIgnoreCase(keywords))
//                    {
//                        goodsPage = tdGoodsService.findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(categoryId, page, size);
//                    }
//                    else
//                    {
//                        goodsPage = tdGoodsService.searchAndFindByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(keywords, categoryId, page, size);
//                    }
//                }
//            }
//        }
        
        if (null == categoryId) {
            if ("isOnSale".equalsIgnoreCase(property)) {
                if ("flashSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsOnSaleTrueAndFlashSaleTrueOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                } else if ("groupSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsOnSaleTrueAndGroupSaleTrueOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                } else {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsOnSaleTrueOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsOnSaleTrueOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                }
            } else if ("isNotOnSale".equalsIgnoreCase(property)) {
                if ("flashSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                } else if ("groupSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                } else {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsOnSaleFalseOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsOnSaleFalseOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                }
            }
            else
            {
                if ("flashSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsFlashSaleTrueOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsFlashSaleTrueOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                } else if ("groupSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByIsGroupSaleTrueOrderBySortIdAsc(page,
                                        size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndIsGroupSaleTrueOrderBySortIdAsc(
                                        keywords, page, size);
                    }
                } else {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService.findAllOrderBySortIdAsc(
                                page, size);
                    } else {
                        goodsPage = tdGoodsService.searchAndOrderBySortIdAsc(
                                keywords, page, size);
                    }
                }
            }
        } else {
            if ("isOnSale".equalsIgnoreCase(property)) {
                if ("flashSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                } else if ("groupSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                } else {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                }
            } else if ("isNotOnSale".equalsIgnoreCase(property)) {
                if ("flashSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                } else if ("groupSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                } else {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsOnSaleFalseOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsOnSaleFalseOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                }
            }
            else
            {
                if ("flashSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsFlashSaleTrueOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsFlashSaleTrueOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                } else if ("groupSale".equalsIgnoreCase(saleType)) {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingAndIsGroupSaleTrueOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdAndIsGroupSaleTrueOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                } else {
                    if (null == keywords || "".equalsIgnoreCase(keywords)) {
                        goodsPage = tdGoodsService
                                .findByCategoryIdTreeContainingOrderBySortIdAsc(
                                        categoryId, page, size);
                    } else {
                        goodsPage = tdGoodsService
                                .searchAndFindByCategoryIdOrderBySortIdAsc(
                                        keywords, categoryId, page, size);
                    }
                }
            }
        }
        
        map.addAttribute("content_page", goodsPage);

        // 参数注回
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("categoryId", categoryId);
        map.addAttribute("property", property);
        
        // 文字列表模式
        if (null != __VIEWSTATE && __VIEWSTATE.equals("lbtnViewTxt"))
        {
            return "/site_mag/goods_txt_list";
        }
        
        // 图片列表模式
        return "/site_mag/goods_pic_list";
    }
    
    @RequestMapping(value="/list/dialog/{type}")
    public String goodsListDialog(@PathVariable String type,
                                String keywords,
                                Long categoryId,
                                Integer page, 
                                Integer size,
                                Integer total,
                                String __EVENTTARGET,
                                String __EVENTARGUMENT,
                                String __VIEWSTATE,
                                ModelMap map,
                                HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSearch"))
            {
                
            }
            else if (__EVENTTARGET.equalsIgnoreCase("categoryId"))
            {
                
            }
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
        
        Page<TdGoods> goodsPage = null;
        
        if (null == categoryId)
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                goodsPage = tdGoodsService.findAllOrderBySortIdAsc(page, size);
            }
            else
            {
                goodsPage = tdGoodsService.searchAndOrderBySortIdAsc(keywords, page, size);
            }
        }
        else
        {
            if (null == keywords || "".equalsIgnoreCase(keywords))
            {
                goodsPage = tdGoodsService.findByCategoryIdTreeContainingOrderBySortIdAsc(categoryId, page, size);
            }
            else
            {
                goodsPage = tdGoodsService.searchAndFindByCategoryIdOrderBySortIdAsc(keywords, categoryId, page, size);
            }
        }
        
        map.addAttribute("goods_page", goodsPage);
        
        // 参数注回
        map.addAttribute("category_list", tdProductCategoryService.findAll());
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("total", total);
        map.addAttribute("keywords", keywords);
        map.addAttribute("categoryId", categoryId);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null != type && type.equalsIgnoreCase("gift"))
        {
            return "/site_mag/dialog_goods_gift_list";
        }
        
        return "/site_mag/dialog_goods_combination_list";
    }
    
    @RequestMapping(value="/edit")
    public String goodsEdit(Long pid, Long id, 
            String __EVENTTARGET,
            String __EVENTARGUMENT,
            String __VIEWSTATE,
            ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        
        map.addAttribute("category_list", tdProductCategoryService.findAll());
                
        if (null != id)
        {
            TdGoods tdGoods = tdGoodsService.findOne(id);
            
            if (null != tdGoods)
            {
                // 参数列表
                TdProductCategory tpc = tdProductCategoryService.findOne(tdGoods.getCategoryId());
                
                if (null != tpc && null != tpc.getParamCategoryId())
                {
//                    map.addAttribute("param_list", tdParameterService.findByCategoryTreeContaining(tpc.getParamCategoryId()));
                }
                
                // 查找产品列表
                map.addAttribute("product_list", tdProductService.findByProductCategoryTreeContaining(tdGoods.getCategoryId()));
            
                // 查找品牌
//                map.addAttribute("brand_list", tdBrandService.findByProductCategoryTreeContaining(tdGoods.getCategoryId()));
                
//                map.addAttribute("warehouse_list", tdWarehouseService.findAll());
                
                if (null != tdGoods.getProductId())
                {
                    map.addAttribute("product", tdProductService.findOne(tdGoods.getProductId()));
                }
                
                map.addAttribute("goods", tdGoods);
            }
        }
        
        return "/site_mag/goods_edit";
    }
    
    @RequestMapping(value = "/copy")
    public String goodsCopy(TdGoods tdGoods, String __VIEWSTATE, ModelMap map,
            HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }

        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        if (null != tdGoods)
        {
            TdGoods newGoods = new TdGoods();
            
            newGoods.setAfterMarketService(tdGoods.getAfterMarketService());
            newGoods.setAveragePoints(tdGoods.getAveragePoints());
 //           newGoods.setBrandId(tdGoods.getBrandId());
            newGoods.setBrandTitle(tdGoods.getBrandTitle());
            newGoods.setCategoryId(tdGoods.getCategoryId());
            newGoods.setCategoryIdTree(tdGoods.getCategoryIdTree());
            newGoods.setCategoryTitle(tdGoods.getCategoryTitle());
            newGoods.setCode(tdGoods.getCode());
            newGoods.setCombList(null);
            newGoods.setConfiguration(tdGoods.getConfiguration());
            newGoods.setCostPrice(tdGoods.getCostPrice());
            newGoods.setCoverImageHeight(tdGoods.getCoverImageHeight());
            newGoods.setCoverImageWidth(tdGoods.getCoverImageWidth());
            newGoods.setCoverImageUri(tdGoods.getCoverImageUri());
            newGoods.setCreateTime(new Date());
            newGoods.setDeliveryArea(tdGoods.getDeliveryArea());
            newGoods.setDetail(tdGoods.getDetail());
            newGoods.setFlashSaleImage(tdGoods.getFlashSaleImage());
            newGoods.setFlashSaleLeftNumber(tdGoods.getFlashSaleLeftNumber());
            newGoods.setFlashSalePrice(tdGoods.getFlashSalePrice());
            newGoods.setFlashSaleSoldNumber(tdGoods.getFlashSaleSoldNumber());
            newGoods.setFlashSaleStartTime(tdGoods.getFlashSaleStartTime());
            newGoods.setFlashSaleStopTime(tdGoods.getFlashSaleStopTime());
            newGoods.setGroupSaleImage(tdGoods.getGroupSaleImage());
            newGoods.setGroupSaleLeftNumber(tdGoods.getGroupSaleLeftNumber());
            newGoods.setGroupSalePrice(tdGoods.getGroupSalePrice());
            newGoods.setGroupSaleSoldNumber(tdGoods.getGroupSaleSoldNumber());
            newGoods.setGroupSaleStartTime(tdGoods.getGroupSaleStartTime());
            newGoods.setGroupSaleStopTime(tdGoods.getGroupSaleStopTime());
            newGoods.setIncludePrice(tdGoods.getIncludePrice());
            newGoods.setIsFlashSale(tdGoods.getIsFlashSale());
            newGoods.setIsGroupSale(tdGoods.getIsGroupSale());
            newGoods.setIsHot(tdGoods.getIsHot());
            newGoods.setIsNew(tdGoods.getIsNew());
            newGoods.setIsOnSale(tdGoods.getIsOnSale());
            newGoods.setIsRecommendIndex(tdGoods.getIsRecommendIndex());
            newGoods.setIsRecommendType(tdGoods.getIsRecommendType());
            newGoods.setIsSpecialPrice(tdGoods.getIsSpecialPrice());
            newGoods.setLeftNumber(tdGoods.getLeftNumber());
            newGoods.setMarketPrice(tdGoods.getMarketPrice());
            newGoods.setName(tdGoods.getName());
            newGoods.setNumberDecType(tdGoods.getNumberDecType());
            newGoods.setOnSaleTime(tdGoods.getOnSaleTime());
            newGoods.setOutFactoryPrice(tdGoods.getOutFactoryPrice());
            
//            List<TdGoodsParameter> paramList = tdGoods.getParamList();
            
//            if (null != paramList && paramList.size() > 0)
//            {
//                List<TdGoodsParameter> newParamList = new ArrayList<TdGoodsParameter>();
//                
//                for (TdGoodsParameter tgp : paramList)
//                {
//                    if (null != tgp)
//                    {
//                        TdGoodsParameter newTgp = new TdGoodsParameter();
//                        
//                        newTgp.setParamCategory(tgp.getParamCategory());
//                        newTgp.setParamId(tgp.getParamId());
//                        newTgp.setParamName(tgp.getParamName());
//                        newTgp.setValue(tgp.getValue());
//                        
//                        newParamList.add(newTgp);
//                    }
//                }
//                
//                newGoods.setParamList(newParamList);
//                
//                tdGoodsParameterService.save(newParamList);
//            }
            
            
            newGoods.setParamValueCollect(tdGoods.getParamValueCollect());
            newGoods.setPointLimited(tdGoods.getPointLimited());
            newGoods.setPriceUnit(tdGoods.getPriceUnit());
//            newGoods.setProductId(tdGoods.getProductId());
            newGoods.setPromotion(tdGoods.getPromotion());
            newGoods.setReturnPoints(tdGoods.getReturnPoints());
            newGoods.setReturnPrice(tdGoods.getReturnPrice());
            newGoods.setSalePrice(tdGoods.getSalePrice());
            newGoods.setSaleType(tdGoods.getSaleType());
            newGoods.setSelectOneValue(tdGoods.getSelectOneValue());
            newGoods.setSelectThreeValue(tdGoods.getSelectThreeValue());
            newGoods.setSelectTwoValue(tdGoods.getSelectTwoValue());
            newGoods.setSeoDescription(tdGoods.getSeoDescription());
            newGoods.setSeoKeywords(tdGoods.getSeoKeywords());
            newGoods.setSeoTitle(tdGoods.getSeoTitle());
            newGoods.setService(tdGoods.getService());
            newGoods.setShowPictures(tdGoods.getShowPictures());
            newGoods.setSoldNumber(tdGoods.getSoldNumber());
            newGoods.setSortId(tdGoods.getSortId());
            newGoods.setSubTitle(tdGoods.getSubTitle());
            newGoods.setTags(tdGoods.getTags());
            newGoods.setTitle(tdGoods.getTitle());
            newGoods.setTotalComb(0);
            newGoods.setTotalComments(0L);
            newGoods.setTotalGift(0);
            newGoods.setUserLevelLimit(tdGoods.getUserLevelLimit());
            newGoods.setVideoUri(tdGoods.getVideoUri());
            newGoods.setWarehouseId(tdGoods.getWarehouseId());
            newGoods.setWarehouseTitle(tdGoods.getWarehouseTitle());
            
            tdGoodsService.save(newGoods, username);
            tdManagerLogService.addLog("add", "用户复制商品", req);
        }

        return "redirect:/Verwalter/goods/list";
    }
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(TdGoods tdGoods, 
                        String[] hid_photo_name_show360,
                        String __EVENTTARGET,
                        String __EVENTARGUMENT,
                        String __VIEWSTATE,
                        String menuId,
                        String channelId,
                        ModelMap map,Boolean isRecommendIndex,Boolean isRecommendType,Boolean isHot,Boolean isNew,Boolean isSpecialPrice,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        String uris = parsePicUris(hid_photo_name_show360);
        
        tdGoods.setShowPictures(uris);
        
        String type = null;
        
        if (null == tdGoods.getId())
        {
            type = "add";
        }
        else
        {
            type = "edit";
        }
        
        /**
		 * @author lc
		 * @注释：推荐类型修改
		 */
        if (null != isRecommendIndex && isRecommendIndex) {
			tdGoods.setIsRecommendIndex(true);
		}else{
			tdGoods.setIsRecommendIndex(false);
		}
        if (null != isRecommendType && isRecommendType) {
			tdGoods.setIsRecommendType(true);
		}else{
			tdGoods.setIsRecommendType(false);
		}
        if (null != isHot && isHot) {
			tdGoods.setIsHot(true);
		}else{
			tdGoods.setIsHot(false);
		}
        if (null != isNew && isNew) {
			tdGoods.setIsNew(true);
		}else{
			tdGoods.setIsNew(false);
		}
        if (null != isSpecialPrice && isSpecialPrice) {
			tdGoods.setIsSpecialPrice(true);
		}else{
			tdGoods.setIsSpecialPrice(false);
		}
        
        tdGoodsService.save(tdGoods, username);
        
        tdManagerLogService.addLog(type, "用户修改商品", req);
        
        return "redirect:/Verwalter/goods/list?__EVENTTARGET=" + __EVENTTARGET
                + "&__EVENTARGUMENT=" + __EVENTARGUMENT
                + "&__VIEWSTATE=" + __VIEWSTATE;
    }
    
    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
            Model model) {
        if (id != null) {
            TdGoods goods = tdGoodsService.findOne(id);
            model.addAttribute("tdGoods", goods);
        }
    }
    
    /**
     * 图片地址字符串整理，多张图片用,隔开
     * 
     * @param params
     * @return
     */
    private String parsePicUris(String[] uris)
    {
        if (null == uris || 0 == uris.length)
        {
            return null;
        }
        
        String res = "";
        
        for (String item : uris)
        {
            String uri = item.substring(item.indexOf("|")+1, item.indexOf("|", 2));
            
            if (null != uri)
            {
                res += uri;
                res += ",";
            }
        }
        
        return res;
    }
    
    /**
     * 修改商品
     * @param cid
     * @param ids
     * @param sortIds
     * @param username
     */
    private void btnSave(Long[] ids, Long[] sortIds, String username)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            TdGoods goods = tdGoodsService.findOne(id);
                
            if (sortIds.length > i)
            {
                goods.setSortId(sortIds[i]);
                tdGoodsService.save(goods, username);
            }
        }
    }
    
    /**
     * 删除商品
     * 
     * @param ids
     * @param chkIds
     */
    private void btnDelete(Long[] ids, Integer[] chkIds)
    {
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
                
                tdGoodsService.delete(id);
            }
        }
    }
    
}
