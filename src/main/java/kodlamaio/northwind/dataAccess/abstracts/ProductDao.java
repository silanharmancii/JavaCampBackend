package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer> {
	
	Product getByProductName(String productName);
	Product getByProductNameAndCategory(String productName, int categoryId);
	List<Product> getByProductNameOrCategory(String productName, int categoryId);
	List<Product> getByCategoryIdIn(List<Integer> categories);
	List<Product> getByProductNameContains(String productName);
	List<Product> getByProductStartsWith(String productName);
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByProductAndCategory(String productName, int categoryId);
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto From"
			+ "(p.id, p.productName,c.categoryName) "
			+ "Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
}
