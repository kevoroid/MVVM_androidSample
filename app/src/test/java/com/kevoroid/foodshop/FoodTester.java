package com.kevoroid.foodshop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kevoroid.foodshop.apis.ApiEndpoints;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.repos.MasterRepo;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FoodTester {

	@Mock
	ApiEndpoints apiEndPoint;

	@Mock
	MasterRepo masterRepo;

	private ProductListViewModel productListViewModel;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		productListViewModel = new ProductListViewModel();
//		masterRepo = MasterRepo.getInstance();
	}

	@Test
	public void addition_isCorrect() {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void testNull() {
		assertNotNull(productListViewModel.getProductList());
//		assertTrue(productListViewModel.getProductList().hasObservers());
	}

	@Test
	public void testRepository() {
		masterRepo.getProductList();
	}

	@Test
	public void fetchProductsTest() {
		Assert.assertEquals(returnProductListFromResources().get(0).getName(), "Food");
		Assert.assertEquals(returnProductListFromResources().get(0).getProducts().size(), 4);
		Assert.assertEquals(returnProductListFromResources().get(0).getProducts().get(3).getSalePrice().getAmount(), "0.01");

//		Type collectionType2 = new TypeToken<Collection<LiveData<ProductList>>>(){}.getType();
//		LiveData<List<ProductList>> products2 = new Gson().fromJson(returnResourcesTeamsData(), collectionType2);

//		Assert.assertSame(productListViewModel.fetchProductList(), products2);

//		productListViewModel.getProductList();
	}

	@Test
	public void testTeamLogosURL() {
		Assert.assertEquals(RetroMaster.returnProductImageUrl(returnProductListFromResources().get(0).getProducts().get(0).getImageUrl()),
				"http://mobcategories.s3-website-eu-west-1.amazonaws.com/Bread.jpg");
		Assert.assertEquals(RetroMaster.returnProductImageUrl(returnProductListFromResources().get(1).getProducts().get(3).getImageUrl()),
				"http://mobcategories.s3-website-eu-west-1.amazonaws.com/Beer.jpg");
	}

	private List<ProductList> returnProductListFromResources() {
		Type collectionType = new TypeToken<Collection<ProductList>>() {
		}.getType();
		return new Gson().fromJson(returnResourcesSteamData(), collectionType);
	}

	private String returnResourcesSteamData() {
		InputStream in = Objects.requireNonNull(this.getClass().getClassLoader()).getResourceAsStream("data.json");

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream.toString();
	}

}