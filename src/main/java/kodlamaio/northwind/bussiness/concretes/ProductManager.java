package kodlamaio.northwind.bussiness.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.bussiness.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService{

	private ProductDao productDao;
	
	@Autowired	
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		
		return new SuccessDataResult<List<Product>>
		(this.productDao.findAll(),"Data listelendi.");
				
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Ürün eklendi.");
	}

	@Override
	public DataResult<Product> getByProductName(String productName) {
		return new SuccessDataResult<Product>
		(this.productDao.getByProductName(productName),"Data listelendi.");
			
	}

	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<Product>
		(this.productDao.getByProductNameAndCategory(productName,categoryId),"Data listelendi.");
		
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameOrCategory(productName, categoryId),"Data listelendi.");
			
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByCategoryIdIn(categories),"Data listelendi.");
			
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameContains(productName),"Data listelendi.");
			
	}

	@Override
	public DataResult<List<Product>> getByProductStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductStartsWith(productName),"Data listelendi.");
			
	}

	@Override
	public DataResult<List<Product>> getByProductAndCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductAndCategory(productName, categoryId),"Data listelendi.");
			
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Product>>
		(this.productDao.findAll(pageable).getContent()) ;
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
		Sort sort= Sort.by(Sort.Direction.DESC,"productName");
		return new SuccessDataResult<List<Product>>(
				this.productDao.findAll(sort),"Başarılı");
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		
		return new SuccessDataResult<List<ProductWithCategoryDto>>
		(this.productDao.getProductWithCategoryDetails(),"Data listelendi.");
	}

}




















