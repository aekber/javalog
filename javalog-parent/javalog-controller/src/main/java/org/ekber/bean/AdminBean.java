package org.ekber.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.ekber.domain.Article;
import org.ekber.domain.Category;
import org.ekber.domain.Comments;
import org.ekber.domain.SubCategory;
import org.ekber.domain.Tag;
import org.ekber.service.interfaces.IArticleService;
import org.ekber.service.interfaces.ICategoryService;
import org.ekber.service.interfaces.ICommentsService;
import org.ekber.service.interfaces.ISubCategoryService;
import org.ekber.service.interfaces.ITagService;
import org.ekber.service.interfaces.IUserRateService;
import org.richfaces.component.UIExtendedDataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private IArticleService articleService;
	private ICategoryService categoryService;
	private ISubCategoryService subCategoryService;
	private ICommentsService commentsService;
	private ITagService tagService;
	private IUserRateService userRateService;
	
	private static Logger logger = LoggerFactory.getLogger(AdminBean.class);
	
	private Category editedCategory = new Category();
	private SubCategory editedSubCategory = new SubCategory();
	private Article editedArticle = new Article();
	private Comments selectedComment = new Comments();
	private Article newArticle = new Article();
    private String newCategoryName, newSubCategoryName, newArticleName, currentType = "", currentType2 = "", articleContent, tags;
    private Collection<Object> selection;
    private List<Article> selectionItems = new ArrayList<Article>();
    private List<Category> allCategories = new ArrayList<Category>();
    private List<SubCategory> allSubCategories = new ArrayList<SubCategory>();
    private List<Article> allArticles = new ArrayList<Article>();
    private List<Comments> articleComments = new ArrayList<Comments>();
    private List<SelectItem> firstList = new ArrayList<SelectItem>();
    private List<SelectItem> secondList = new ArrayList<SelectItem>();
    private List<SelectItem> thirdList = new ArrayList<SelectItem>();
    private int page = 1;
    
    public AdminBean(){
    	newArticle.setSubcategory(new SubCategory());
    	newArticle.getSubcategory().setCategory(new Category());
    	
    	editedArticle.setSubcategory(new SubCategory());
    	editedArticle.getSubcategory().setCategory(new Category());
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

	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}
	
	public void setUserRateService(IUserRateService userRateService) {
		this.userRateService = userRateService;
	}

	public List<Category> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}
	
    public String getNewCategoryName() {
		return newCategoryName;
	}

	public void setNewCategoryName(String newCategoryName) {
		this.newCategoryName = newCategoryName;
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public List<Article> getSelectionItems() {
		return selectionItems;
	}

	public void setSelectionItems(List<Article> selectionItems) {
		this.selectionItems = selectionItems;
	}

	public List<Comments> getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(List<Comments> articleComments) {
		this.articleComments = articleComments;
	}

	public Category getEditedCategory() {
		return editedCategory;
	}

	public void setEditedCategory(Category editedCategory) {
		this.editedCategory = editedCategory;
	}

	public List<Article> getAllArticles() {
		return allArticles;
	}

	public void setAllArticles(List<Article> allArticles) {
		this.allArticles = allArticles;
	}

	public List<SubCategory> getAllSubCategories() {
		return allSubCategories;
	}

	public void setAllSubCategories(List<SubCategory> allSubCategories) {
		this.allSubCategories = allSubCategories;
	}

	public SubCategory getEditedSubCategory() {
		return editedSubCategory;
	}

	public void setEditedSubCategory(SubCategory editedSubCategory) {
		this.editedSubCategory = editedSubCategory;
	}

	public String getNewSubCategoryName() {
		return newSubCategoryName;
	}

	public void setNewSubCategoryName(String newSubCategoryName) {
		this.newSubCategoryName = newSubCategoryName;
	}

	public String getNewArticleName() {
		return newArticleName;
	}

	public void setNewArticleName(String newArticleName) {
		this.newArticleName = newArticleName;
	}

	public List<SelectItem> getFirstList() {
		return firstList;
	}

	public void setFirstList(List<SelectItem> firstList) {
		this.firstList = firstList;
	}

	public List<SelectItem> getSecondList() {
		return secondList;
	}

	public void setSecondList(List<SelectItem> secondList) {
		this.secondList = secondList;
	}

	public List<SelectItem> getThirdList() {
		return thirdList;
	}

	public void setThirdList(List<SelectItem> thirdList) {
		this.thirdList = thirdList;
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

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Article getEditedArticle() {
		return editedArticle;
	}

	public void setEditedArticle(Article editedArticle) {
		this.editedArticle = editedArticle;
	}

	public Article getNewArticle() {
		return newArticle;
	}

	public void setNewArticle(Article newArticle) {
		this.newArticle = newArticle;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Comments getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(Comments selectedComment) {
		this.selectedComment = selectedComment;
	}

	public String createCategory(){
    	Category c = new Category();
    	c.setArticlecategory(this.getNewCategoryName());
    	
    	categoryService.addNewCategory(c);
    	allCategories.add(c);
    	firstList.add(new SelectItem(this.getNewCategoryName()));
    	this.setNewCategoryName("");
    	
    	return null;
    }
	
	public String createSubCategory(){
    	Category category = null;
		
    	SubCategory subCategory = new SubCategory();
    	subCategory.setSubCategoryName(this.getNewSubCategoryName());
    	
    	for(Category c : allCategories){
    		if(this.getCurrentType().equals(c.getArticlecategory())){
    			category = c;
    			break;
    		}
    	}
    	
    	subCategory.setCategory(category);
    	subCategoryService.addNewSubCategory(subCategory);
    	allSubCategories.add(subCategory);
    	this.setNewSubCategoryName("");
    	
    	return null;
    }
	
	public String createArticle(){
    	Category category = null;
    	SubCategory subCategory = null;

    	String articleCategory = this.getNewArticle().getSubcategory().getCategory().getArticlecategory();
    	String articleSubCategory = this.getNewArticle().getSubcategory().getSubCategoryName();    			
    	List<Tag> tagList = new ArrayList<Tag>();
    	
    	for(Category c : allCategories){
    		for(SubCategory s : c.getSubCat()){
	    		if(articleCategory.equals(c.getArticlecategory()) && articleSubCategory.equals(s.getSubCategoryName())){
	    			category = c;
	    			subCategory = s;
	    			break;
	    		}
    		}
    	}
    	
    	for(String name : this.getTags().split(",")){
	    	Tag tag = new Tag();
	    	tag.setTagName(name);
	    	tag.setArticleTag(this.getNewArticle());
	    	tagList.add(tag);
    	}
    	
    	subCategory.setCategory(category);
    	this.getNewArticle().setSubcategory(subCategory);
    	this.getNewArticle().setArticleTags(tagList);
    	articleService.addNewArticle(this.getNewArticle());
    	allArticles.add(this.getNewArticle());
    	this.setNewArticleName("");
    	newArticle = new Article();
		
		return null;
    }
	
    public void selectionListener(AjaxBehaviorEvent event) {
        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectionItems.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                selectionItems.add((Article) dataTable.getRowData());
                this.setArticleComments(((Article) dataTable.getRowData()).getComments());
            }
        }
        dataTable.setRowKey(originalKey);
    }
    
    public void commentListener(AjaxBehaviorEvent event) {
    	Boolean value = (Boolean) ((UIInput) event.getComponent()).getValue();
        FacesContext context = FacesContext.getCurrentInstance();
        Comments comment = context.getApplication().evaluateExpressionGet(context, "#{comment}", Comments.class);

        if(value){
    		if(comment.isActivated()){
    			comment.setActivated(true);
    			commentsService.updateComment(comment);
    			System.out.println("Yorum aktiflestirildi...");
    		}
    	}
    	else{
    		if(!comment.isActivated()){
    			comment.setActivated(false);
    			commentsService.updateComment(comment);
    			System.out.println("Yorum pasiflestirildi...");
    		}
    	}
    }
    
    public void updateCategory() {
    	categoryService.updateCategory(this.getEditedCategory());
    }
    
    public void updateSubCategory() {
    	subCategoryService.updateSubCategory(this.getEditedSubCategory());
    }
	
    public void deleteCategory() {
    	
    	//TODO:Benzer i�lemler article daki subcategory i�inde yap�lacak
    	removeCategoryFromCombobox();
    	
    	categoryService.deleteCategory(this.getEditedCategory());
    	allCategories.remove(this.getEditedCategory());
    }
    
    private void removeCategoryFromCombobox() {
		for(SelectItem item : firstList){
			if(item.getValue().equals(this.getEditedCategory().getArticlecategory())){
				firstList.remove(item);
				break;
			}
		}
		
	}
    
    public String deleteComment(int commentId){

    	Comments c = commentsService.find(commentId);
    	commentsService.deleteComment(c);
    	this.getArticleComments().remove(c);
    	
    	for(Article a : this.getAllArticles()){
    		if(a.getArticleid() == this.getSelectedComment().getArticles().getArticleid()){
    			a.getComments().remove(this.getSelectedComment());
    			break;
    		}
    	}
    	
    	return null;
    }

	public void deleteSubCategory() {
    	subCategoryService.deleteSubCategory(this.getEditedSubCategory());
    	allSubCategories.remove(this.getEditedSubCategory());
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
    
	@PostConstruct
	public void init() {
        SelectItem item = new SelectItem("");
        firstList.add(item);
        
		allCategories = categoryService.getAllCategory();
		for(Category c : allCategories){
	        item = new SelectItem(c.getArticlecategory());
	        firstList.add(item);
			for(SubCategory s : c.getSubCat()){
				allSubCategories.add(s);
				for(Article a : s.getArticles()){
					allArticles.add(a);
				}
			}
		}
	}
	
}
