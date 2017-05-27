package org.ekber.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.tree.TreeNode;

import org.ekber.domain.Article;
import org.ekber.domain.Category;
import org.ekber.domain.Comments;
import org.ekber.domain.SubCategory;
import org.ekber.domain.TagDTO;
import org.ekber.domain.UserRate;
import org.ekber.service.interfaces.IArticleService;
import org.ekber.service.interfaces.ICategoryService;
import org.ekber.service.interfaces.ICommentsService;
import org.ekber.service.interfaces.ISubCategoryService;
import org.ekber.service.interfaces.ITagService;
import org.ekber.service.interfaces.IUserRateService;
import org.ekber.utils.FacesSupport;
import org.ekber.utils.SortByArticleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MakaleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TagDTO> tagCloud = new ArrayList<TagDTO>();
	private List<Article> popularArticles;
	private List<Article> latestArticles;
	private List<Article> recentArticles;
	private List<Article> articleSearchResult = new ArrayList<Article>();
	private List<Article> allArticles = new ArrayList<Article>();
	private List<Article> articlesByTag = new ArrayList<Article>();
	private List<Comments> approvedComments = new ArrayList<Comments>();
	private List<Category> allCategories = new ArrayList<Category>();
	private List<TreeNode> rootNodes = new ArrayList<TreeNode>();
	private List<TreeNode> archiveNodes = new ArrayList<TreeNode>();
	private Map<String, Article> map;
	private Map<String, SubCategory> mapSubCat;
	private Map<String, List<UserRate>> mapUserRate;
	private Article selectedArticle = new Article();
	private SubCategory selectedSubCat = new SubCategory();
	private Comments newComment = new Comments();
	private TreeNode currentSelection;
	private String searchText;
	private String articleTag;
	private String averageRate;
	private Integer totalRate;
    private String validate;
	private String rateAverage;
	private String selectedTag;

    private String currentType = "";
    private String currentType2 = "";
    private String currentType3 = "";
    private List<SelectItem> firstList = new ArrayList<SelectItem>();
    private List<SelectItem> secondList = new ArrayList<SelectItem>();
    private List<SelectItem> thirdList = new ArrayList<SelectItem>();
    
    private String newCategoryName;
    
    private FacesSupport facesSupport;
	private IArticleService articleService;
	private ICategoryService categoryService;
	private ISubCategoryService subCategoryService;
	private ICommentsService commentsService;
	private ITagService tagService;
	private IUserRateService userRateService;
	
	private static Logger logger = LoggerFactory.getLogger(MakaleBean.class);
	    
    
	
	public String getRateAverage() {
		int sum = 0, count = 0;
		
		if(this.getRateList().size() > 0){
			for(UserRate r : this.getRateList()){
				sum += r.getRate();
				count++;
			}
			
			NumberFormat format = NumberFormat.getNumberInstance();
			format.setMinimumFractionDigits(2);
			format.setMaximumFractionDigits(2);
			
			if(count != 0){
				rateAverage = format.format((float)sum/count).replace(',', '.');
			}else{
				rateAverage = "";
			}
			
		}else{
			rateAverage = "";
		}
		return rateAverage;
	}
	
	public void setRateAverage(String rateAverage) {
		this.rateAverage = rateAverage;
	}

	public List<UserRate> getRateList() {
		return mapUserRate.get(this.getSelectedArticle().getArticleid().toString());
	}
	
	public void setFacesSupport(FacesSupport facesSupport) {
		this.facesSupport = facesSupport;
	}
	
	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setSubCategoryService(ISubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	public void setUserRateService(IUserRateService userRateService) {
		this.userRateService = userRateService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public List<Article> getPopularArticles() {
		return popularArticles;
	}

	public void setPopularArticles(List<Article> popularArticles) {
		this.popularArticles = popularArticles;
	}

	public List<Article> getLatestArticles() {
		return latestArticles;
	}

	public void setLatestArticles(List<Article> latestArticles) {
		this.latestArticles = latestArticles;
	}

	public List<Article> getRecentArticles() {
		return recentArticles;
	}

	public List<Article> getAllArticles() {
		return allArticles;
	}

	public void setAllArticles(List<Article> allArticles) {
		this.allArticles = allArticles;
	}

	public void setRecentArticles(List<Article> recentArticles) {
		this.recentArticles = recentArticles;
	}

	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	}

	public List<TagDTO> getTagCloud() {
		return tagService.getTags();
	}

	public void setTagCloud(List<TagDTO> tagCloud) {
		this.tagCloud = tagCloud;
	}

	public List<Category> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}

	public String getSelectedTag() {
		FacesContext jsf = FacesContext.getCurrentInstance();
		ExternalContext extCtxt = jsf.getExternalContext();
		String tag = extCtxt.getRequestParameterMap().get("tagName");
		
		if(tag != null){
			this.setArticlesByTag(tagService.getArticlesByTag(tag));
		}

		return tag;
	}

	public void setSelectedTag(String selectedTag) {
		this.selectedTag = selectedTag;
	}

	public String getAverageRate() {		
		return averageRate;
	}

	public void setAverageRate(String averageRate) {
		this.averageRate = averageRate;
	}

	public Integer getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(Integer totalRate) {
		this.totalRate = totalRate;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	public List<TreeNode> getRootNodes() {
		return rootNodes;
	}

	public void setRootNodes(List<TreeNode> rootNodes) {
		this.rootNodes = rootNodes;
	}

	public List<TreeNode> getArchiveNodes() {
		return archiveNodes;
	}

	public void setArchiveNodes(List<TreeNode> archiveNodes) {
		this.archiveNodes = archiveNodes;
	}

	public List<Article> getArticlesByTag() {
		return articlesByTag;
	}

	public void setArticlesByTag(List<Article> articlesByTag) {
		this.articlesByTag = articlesByTag;
	}

	public String getNewCategoryName() {
		return newCategoryName;
	}

	public void setNewCategoryName(String newCategoryName) {
		this.newCategoryName = newCategoryName;
	}

	public void setApprovedComments(List<Comments> approvedComments) {
		this.approvedComments = approvedComments;
	}

	public SubCategory getSelectedSubCat() {
		
		System.out.println();
		System.out.println("getSelectedSubCat() metodu cagirildi...");
		
		FacesContext jsf = FacesContext.getCurrentInstance();
		ExternalContext extCtxt = jsf.getExternalContext();
		HttpServletResponse res = (HttpServletResponse) extCtxt.getResponse();
		String category = extCtxt.getRequestParameterMap().get("subcatId");
		
		
		if (category != null) {	
			
			SubCategory sc = mapSubCat.get(category);
			
			if(sc != null){
				
				if(sc.getSubCategoryName().contains("+")){
					sc.setSubCategoryName(sc.getSubCategoryName().replace('+', '-'));
				}else if(sc.getSubCategoryName().contains("%20")){
					sc.setSubCategoryName(sc.getSubCategoryName().replace("%20", "-"));
				}else if(sc.getSubCategoryName().contains(" ")){
					sc.setSubCategoryName(sc.getSubCategoryName().replace(" ", "-"));
				}
				
				this.setSelectedSubCat(sc);
				
			}else{
				
				try {
					res.sendRedirect(extCtxt.getRequestContextPath() +  "/categorynotfound.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jsf.responseComplete();
			}
			
		}else{
			
			try {
				res.sendRedirect(extCtxt.getRequestContextPath() +  "/categorynotfound.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			jsf.responseComplete();
		}
		
		return selectedSubCat;
	}

	public void setSelectedSubCat(SubCategory selectedSubCat) {
		this.selectedSubCat = selectedSubCat;
	}
	
	public String logout(){
		try {
			FacesContext jsf = FacesContext.getCurrentInstance();
			ExternalContext extCtxt = jsf.getExternalContext();
			HttpServletRequest req = (HttpServletRequest) extCtxt.getRequest();		
		
			req.logout();
			
			this.getNewComment().setSender("");
			this.getNewComment().setSenderMail("");
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		return "home?faces-redirect=true";
	}

	public String getArticleTag() {
		
		System.out.println();
		System.out.println("getArticleTag() metodu cagirildi...");
		
		FacesContext jsf = FacesContext.getCurrentInstance();
		ExternalContext extCtxt = jsf.getExternalContext();
		HttpServletRequest req = (HttpServletRequest) extCtxt.getRequest();
		HttpServletResponse res = (HttpServletResponse) extCtxt.getResponse();
		String article = extCtxt.getRequestParameterMap().get("articleId");

		if(req.isUserInRole("admin")){
			this.getNewComment().setSender("admin");
			this.getNewComment().setSenderMail("admin@javalog.net");
		}
		
		if (article != null) {
			
			Article a = map.get(article);
			
			if(a != null){
				
				if(a.getArticleTag().contains("+")){
					a.setArticleTag(a.getArticleTag().replace('+', '-'));
				}else if(a.getArticleTag().contains("%20")){
					a.setArticleTag(a.getArticleTag().replace("%20", "-"));
				}else if(a.getArticleTag().contains(" ")){
					a.setArticleTag(a.getArticleTag().replace(" ", "-"));
				}
				
				synchronized (a) {
					a.setPopularity(a.getPopularity() + 1);
					articleService.updateArticle(a);					
				}
				
				this.setArticleTag(a.getArticleTag());
				this.setSelectedArticle(a);				
				
			} else{
				
				try {
					res.sendRedirect(extCtxt.getRequestContextPath() +  "/articlenotfound.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jsf.responseComplete();
			}
			
		}else{
			try {
				res.sendRedirect(extCtxt.getRequestContextPath() +  "/articlenotfound.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			jsf.responseComplete();
		}
		
		return articleTag;
	}

	public List<Comments> getApprovedComments() {
		List<Comments> comments = this.getSelectedArticle().getComments();		
		List<Comments> commentList = new ArrayList<Comments>();
		
			for(Comments c : comments){
				if(c.isActivated()){
					commentList.add(c);
				}
			}

		return commentList;
	}

	public void setArticleTag(String articleTag) {
		this.articleTag = articleTag;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Article> getArticleSearchResult() {
		return articleSearchResult;
	}

	public void setArticleSearchResult(List<Article> articleSearchResult) {
		this.articleSearchResult = articleSearchResult;
	}

	public Article getSelectedArticle() {
		return selectedArticle;
	}
	
	public void setSelectedArticle(Article selectedArticle) {
		this.selectedArticle = selectedArticle;
	}

	public Comments getNewComment() {
		return newComment;
	}

	public void setNewComment(Comments newComment) {
		this.newComment = newComment;
	}
	
    public TreeNode getCurrentSelection() {
        return currentSelection;
    }
 
    public void setCurrentSelection(TreeNode currentSelection) {
        this.currentSelection = currentSelection;
    }
	
    private boolean executeCaptcha() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        String parm = this.getValidate();
        String c = (String) session.getAttribute("captcha_key_name");
        if (parm.equals(c)) {
            return true;
        } else {
            return false;
        }
    }    
	
    //TODO:Admin degisiklikleri,bunlarý ayrý bir bean classýna al ve 
    //bean classlarýný thread safe yap(non-global variables)
    public String createCategory(){
    	Category c = new Category();
    	c.setArticlecategory(this.getNewCategoryName());
    	
    	categoryService.addNewCategory(c);
    	allCategories.add(c);
    	this.setNewCategoryName("");
    	
    	return null;
    }
    
    public String addComment(){
		FacesContext jsf = FacesContext.getCurrentInstance();
		ExternalContext extCtxt = jsf.getExternalContext();
		HttpServletRequest req = (HttpServletRequest) extCtxt.getRequest();
		
		Comments comment = this.getNewComment();
		comment.setArticles(this.getSelectedArticle());
		comment.setAdmin(req.isUserInRole("admin"));
		comment.setSenderIp(req.getRemoteAddr());
    	commentsService.addComment(comment);
    	this.getSelectedArticle().getComments().add(comment);
    	newComment = new Comments();
    	
    	return null;
    }
    
	
	/**
	 * Bu method local ortama gore gelen ay numarasini kullanarak o ayin local ismini donuyor.
	 * Orn,turkce local de 07 degeri icin "temmuz" donmektedir.
	 * @param format
	 * @param date
	 * @return String
	 */
	
	private String getMonthName(String month){
		DateFormatSymbols symbols = new DateFormatSymbols(new Locale("tr","TR"));
		return symbols.getMonths()[Integer.parseInt(month) - 1];
	}
	
	
	/**
	 * Bu method arsiv kismi icin calismaktadir.Gorevi,aylara gore makaleleri
	 * ve bu aylarida yillara ekleyerek gruplama yapmaktir.Yani hangi makale hangi yilin hangi ayina ait?
	 * sorusuna cevap vermektedir.
	 * @param articles
	 * @return Map
	 */
	
	private TreeMap<String, TreeMap<String, List<MonthlyArticle>>> getFormattedArticles(List<Article> articles){
		String year;
		String month;
		
		SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat=new SimpleDateFormat("MM"); 
		
		TreeMap<String, TreeMap<String, List<MonthlyArticle>>> archiveMap = new TreeMap<String, TreeMap<String,List<MonthlyArticle>>>();
		TreeMap<String, List<MonthlyArticle>> monthMap = new TreeMap<String, List<MonthlyArticle>>();
		List<MonthlyArticle> monthlyArticle = new ArrayList<MonthlyArticle>();
		MonthlyArticle m;

		
		for(Article a : articles){
			year = yearFormat.format(a.getInsertdate());
			month = monthFormat.format(a.getInsertdate());
			
			if(archiveMap.get(year) == null){
				
				monthMap = new TreeMap<String, List<MonthlyArticle>>();
				archiveMap.put(year, monthMap);
				
				if(!monthMap.containsKey(month)){
					
					monthlyArticle = new ArrayList<MonthlyArticle>();
					m = new MonthlyArticle();
					m.setName(a.getArticleTag());
					m.setArticleId(a.getArticleid().toString());
					monthlyArticle.add(m);
					monthMap.put(month, monthlyArticle);
					
				}else{
					m = new MonthlyArticle();
					m.setName(a.getArticleTag());
					m.setArticleId(a.getArticleid().toString());
					monthMap.get(month).add(m);
				}
			}else{
				monthMap = archiveMap.get(year);
				
				if(!monthMap.containsKey(month)){
					monthlyArticle = new ArrayList<MonthlyArticle>();
					m = new MonthlyArticle();
					m.setName(a.getArticleTag());
					m.setArticleId(a.getArticleid().toString());
					monthlyArticle.add(m);
					monthMap.put(month, monthlyArticle);
				}else{
					m = new MonthlyArticle();
					m.setName(a.getArticleTag());
					m.setArticleId(a.getArticleid().toString());
					monthMap.get(month).add(m);
				}
			}
		}
		
		return archiveMap;
	}
	
    public List<SelectItem> getFirstList() {
        return firstList;
    }
 
    public List<SelectItem> getSecondList() {
        return secondList;
    }
 
    public String getCurrentType3() {
		return currentType3;
	}

	public void setCurrentType3(String currentType3) {
		this.currentType3 = currentType3;
	}

	public List<SelectItem> getThirdList() {
		return thirdList;
	}

	public void setThirdList(List<SelectItem> thirdList) {
		this.thirdList = thirdList;
	}

	public void valueChanged(ValueChangeEvent event) {
        secondList.clear();
        if (null != event.getNewValue() && !"".equals(event.getNewValue().toString())) {    		
        	Category c = categoryService.findByCategory(event.getNewValue().toString());
        	SelectItem item = new SelectItem(""); 
            secondList.add(item);
        	for(SubCategory sc : c.getSubCat()){
               item = new SelectItem(sc.getSubCategoryName()); 
               secondList.add(item);
        	}
        } else{
    		System.out.println("***********************************************");
    		System.out.println("event.getNewValue() bos geldiiiiii");
    		System.out.println("***********************************************");
    		return;
    	}
    }
	
	public void valueChanged2(ValueChangeEvent event) {
        thirdList.clear();
        if (null != event.getNewValue() && !"".equals(event.getNewValue().toString())) {    		
        	SubCategory sc = subCategoryService.findBySubCategory(event.getNewValue().toString());
        	SelectItem item = new SelectItem(""); 
            thirdList.add(item);
        	for(Article a : sc.getArticles()){
               item = new SelectItem(a.getArticleTag()); 
               thirdList.add(item);
        	}
        } else{
    		System.out.println("***********************************************");
    		System.out.println("event.getNewValue2() bos geldiiiiii");
    		System.out.println("***********************************************");
    		return;
    	}
    }
 
    public String getCurrentType() {
        return currentType;
    }
 
    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }
    
    public String getCurrentType2() {
        return currentType2;
    }
 
    public void setCurrentType2(String currentType2) {
        this.currentType2 = currentType2;
    }

	
    /*
      Cok fazla select atilmasinin sebebi burasi...burdaki servis cagrilari 
	  category den 1 tane bisi cagirirken, o bitanenin altindaki n tane seye de select atiyor.
	  Yani n tane category icin altindaki n tane sey icin select atiyor.n*n select demek...
	 */
	@PostConstruct
	public void init() {
		
        SelectItem item = new SelectItem("");
        firstList.add(item);
        
		
		map = new HashMap<String, Article>();
		mapSubCat = new HashMap<String, SubCategory>();
		mapUserRate = new HashMap<String, List<UserRate>>();
		List<UserRate> lur = new ArrayList<UserRate>();
		
		allCategories = categoryService.getAllCategory();
		for(Category c : allCategories){
			this.getRootNodes().add(c);
	        item = new SelectItem(c.getArticlecategory());
	        firstList.add(item);
			for(SubCategory s : c.getSubCat()){
				mapSubCat.put(s.getSubcatId().toString(), s);
				for(Article a : s.getArticles()){
					map.put(a.getArticleid().toString(), a);
					allArticles.add(a);
					for(UserRate u : a.getArticleRates()){
						lur.add(u);
					}
					mapUserRate.put(a.getArticleid().toString(), lur);
					lur.clear();
				}
			}
		}
		
		
		TreeMap<String, TreeMap<String, List<MonthlyArticle>>> archiveMap = getFormattedArticles(allArticles);
		String year, month, monthName;
		TreeMap<String, List<MonthlyArticle>> months;
		List<MonthlyArticle> list;
		List<Month> monthList;
				
		for(Map.Entry<String, TreeMap<String, List<MonthlyArticle>>> entry : archiveMap.entrySet()){
			year = entry.getKey();
			months = entry.getValue();
			Year y = new Year();
			y.setName(year);
			monthList = new ArrayList<Month>();
			for(Map.Entry<String, List<MonthlyArticle>> ent : months.entrySet()){
				month = ent.getKey();
				list = ent.getValue();
				monthName = getMonthName(month);
				
				Month m = new Month();
				m.setName(monthName);
				m.setYear(y);
				m.setParent(y);
				m.setArtList(list);
				monthList.add(m);
				for(MonthlyArticle ma : list){
					MonthlyArticle ma2 = new MonthlyArticle();
					ma2.setName(ma.getName());
					ma2.setArticleId(ma.getArticleId());
					ma2.setMonth(m);
					ma2.setParent(m);
				}
			}
			y.setMonthList(monthList);
			this.getArchiveNodes().add(y);
		}
		
		Collections.sort(allArticles, new SortByArticleName());
		
		popularArticles = articleService.getMostPopularArticles(allArticles);
		latestArticles = articleService.getLatestArticles(allArticles);
		recentArticles = categoryService.getRecentArticlesInPerCategory(allCategories);
	}
	
}
