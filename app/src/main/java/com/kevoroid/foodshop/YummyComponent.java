package com.kevoroid.foodshop;

import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.repos.MasterRepo;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component
public interface YummyComponent {

	MasterRepo getRepo();
}
