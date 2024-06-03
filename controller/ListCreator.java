package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Caregiver;
import model.CaregiverDataset;
import model.SearchInfo;

// 검색기능
public class ListCreator {
	private CaregiverDataset memberList;
	private ArrayList<Caregiver> showList; // 조건에 맞는 걸 10개씩 뽑아온 리스트
	private ArrayList<Caregiver> searchedList; // 전체 리스트

	public static SearchInfo info;
	private int ctnMax;

	public ListCreator(int ctn) {
		showList = new ArrayList<Caregiver>();
		searchedList = new ArrayList<Caregiver>();
		memberList = CaregiverDataset.getInstance();

		for (Caregiver c : memberList.getList()) {
			
			if(info == null) {
				searchedList = memberList.getList();
				break;
			}
			
			if(info.getNameInfo().equals("") && info.getGenderInfo().equals("선택") && info.getServiceInfo().equals("선택") && info.getPriceInfo() == -1 && info.getRegionInfo().equals("선택")) {
				searchedList = memberList.getList();
				break;
			}
			
			boolean nameMatch = info.getNameInfo().isEmpty() || c.getName().toLowerCase().contains(info.getNameInfo().toLowerCase());
		    boolean serviceMatch = info.getServiceInfo().equals("선택") || info.getServiceInfo().equals(c.getProvidingServiceTypes());
		    boolean priceMatch = info.getPriceInfo() == -1 || info.getPriceInfo() >= c.getRatesPerServiceType();
		    boolean genderMatch = info.getGenderInfo().equals("선택") || info.getGenderInfo().equals(c.getGender());
		    boolean regionMatch = info.getRegionInfo().equals("선택") || info.getRegionInfo().equals(c.getRegion());

		    if (!info.getNameInfo().isEmpty() && nameMatch && serviceMatch && priceMatch && genderMatch && regionMatch) {
		    	searchedList.add(c);
		    }
		    else if (!info.getServiceInfo().equals("선택") && nameMatch && serviceMatch && priceMatch && genderMatch && regionMatch) {
		    	searchedList.add(c);
		    }
		    else if (!(info.getPriceInfo() == -1) && nameMatch && serviceMatch && priceMatch && genderMatch && regionMatch) {
		    	searchedList.add(c);
		    }
		    else if (!info.getGenderInfo().equals("선택") && nameMatch && serviceMatch && priceMatch && genderMatch && regionMatch) {
		    	searchedList.add(c);
		    }
		    else if(!info.getRegionInfo().equals("선택") && nameMatch && serviceMatch && priceMatch && genderMatch && regionMatch) {
		    	searchedList.add(c);
		    }
			
		}

		Collections.sort(memberList.getList(), new Comparator<Caregiver>() {

			@Override
			public int compare(Caregiver o1, Caregiver o2) {
				if(o1.getReputation() < o2.getReputation()) {
					return 1;
				}else if(o1.getReputation() > o2.getReputation()) {
					return -1;
				}else {
					return 0;
				}
			}
		});

		ctnMax = searchedList.size() / 10;

		createList(ctn);
	}

	public void createList(int ctn) {
		for (int i = 0; i < 10; i++) {
			int a = i + (ctn * 10);
			if (a < searchedList.size()) {
				getShowList().add(searchedList.get(a));
			} else
				break;
		}
	}

	public int getCtnMax() {
		return ctnMax;
	}

	public ArrayList<Caregiver> getShowList() {
		return showList;
	}
}
