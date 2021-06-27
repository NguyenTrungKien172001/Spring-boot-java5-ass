package com.ass.ultis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.ass.dto.ItemDTO;
import com.ass.entity.Product;
import com.ass.mapper.ItemMapper;
import com.ass.repositories.ProductRepository;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ItemMapper mapper;
	
	Map<Integer, ItemDTO> map = new HashMap<>();

	@Override
	public ItemDTO add(Integer id) {
		ItemDTO item = map.get(id);
		
		if(item == null) {
			Product product = this.productRepo.getOne(id);
			item = this.mapper.convertToDTO(product);
			
			item.setQuantity(1);
			map.put(id, item);
		}else {
			item.setQuantity(item.getQuantity()+1);
		}
		
		return item;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		map.remove(id);
	}

	@Override
	public ItemDTO update(Integer id, int qty) {
		// TODO Auto-generated method stub
		Product product = this.productRepo.getOne(id);
		if(product.getAvailable() < qty) {
			
		}
		ItemDTO item = map.get(id);
		item.setQuantity(qty);
		
		if (item.getQuantity() <= 0) {
			map.remove(id);
		}
		return item;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		map.clear();
	}

	@Override
	public Collection<ItemDTO> getItems() {
		// TODO Auto-generated method stub
		return map.values();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return map.values().stream().mapToInt(item -> item.getQuantity()).sum();
	}

	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		
		return  map.values().stream().mapToDouble(item -> item.getPrice()*item.getQuantity()).sum();
	}

}
