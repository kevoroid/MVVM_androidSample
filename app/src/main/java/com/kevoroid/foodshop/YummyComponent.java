package com.kevoroid.foodshop;

import com.kevoroid.foodshop.apis.ApiEndpoints;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.repos.MasterRepo;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = RetroMaster.class)
public interface YummyComponent {

	MasterRepo getRepo();

	ApiEndpoints getRetroMaster();
}
