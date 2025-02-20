package com.demo.xyzmc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.xyzmc.entity.Admin;
import com.demo.xyzmc.entity.MobileNumber;
import com.demo.xyzmc.entity.PlanType;
import com.demo.xyzmc.entity.Status;
import com.demo.xyzmc.entity.TalkTimePlan;
import com.demo.xyzmc.repository.AdminRepository;
import com.demo.xyzmc.repository.MobileNumberRepository;
import com.demo.xyzmc.repository.TalkTimePlansRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class XyzmobileconnectionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(XyzmobileconnectionApplication.class, args);
	}

	private final AdminRepository adminRepository;
	private final TalkTimePlansRepository talkTimePlansRepository;
	private final MobileNumberRepository mobileNumberRepository;

	@Override
	public void run(String... args) throws Exception {
//		addAdmin();
//		addTalkTimePlans();
//		addMobile();
	}

	List<TalkTimePlan> plans = new ArrayList<>();
	List<MobileNumber> mobileNumbers = new ArrayList<>();
	List<Admin> admins = new ArrayList<>();

	private void addAdmin() {
		admins.add(new Admin(1l, "venkat", "venkat@gmail.com", "abcd"));
		admins.add(new Admin(2l, "yash", "vijay@gmail.com", "abcd"));
		admins.add(new Admin(3l, "komal", "komal@gmail.com", "abcd"));
		adminRepository.saveAll(admins);

	}

	private void addTalkTimePlans() {
		plans.add(new TalkTimePlan(1l, PlanType.ANUALLY, 1499.0, 365));
		plans.add(new TalkTimePlan(2l, PlanType.MONTHLY, 260.0, 28));
		plans.add(new TalkTimePlan(3l, PlanType.QUARTERLY, 666.0, 84));
		talkTimePlansRepository.saveAll(plans);
	}

	private void addMobile() {
		mobileNumbers.add(new MobileNumber(1l, 9876598778l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(2l, 9876598768l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(3l, 9876598756l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(4l, 9876598734l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(5l, 9876598790l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(6l, 9876598721l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(7l, 9876598726l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(8l, 9876598757l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(9l, 9876598775l, Status.AVAILABLE));
		mobileNumbers.add(new MobileNumber(10l, 9876598761l, Status.AVAILABLE));
		mobileNumberRepository.saveAll(mobileNumbers);
	}

}
