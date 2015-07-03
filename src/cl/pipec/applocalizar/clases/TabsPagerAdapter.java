package cl.pipec.applocalizar.clases;

import cl.pipec.applocalizar.BuscarFragment;
import cl.pipec.applocalizar.HomeFragment;
import cl.pipec.applocalizar.ListarFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>(); 

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		switch (index) {
        case 0:
            // HomeFragment fragment activity
            return new HomeFragment();
        case 1:
            // ListarFragment fragment activity
        	Fragment fragment = new ListarFragment();
            Bundle args = new Bundle();
            //args.putInt(SectionFragment.ARG_SECTION_NUMBER, position + 1);
            //fragment.setArguments(args);
            //registeredFragments.put(position, fragment);
            return fragment;
            //return new ListarFragment();
        case 2:
            // BuscarFragment fragment activity
            return new BuscarFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
	
	public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}
