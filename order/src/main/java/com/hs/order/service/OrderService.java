package com.hs.order.service;

import com.hs.order.dao.OrderDao;
import com.hs.common.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Resource
    private OrderDao orderDao;


    public void order(Order order, MultipartFile material) {
        try {
            if (material != null && !material.getOriginalFilename().equals("")) {
                String homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "/test/" + order.getCompany();
                File directory = new File(homeDirectory);
                File infoDirectory = new File(homeDirectory + "info");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                if (!infoDirectory.exists()) {
                    infoDirectory.mkdirs();
                }
                String path = homeDirectory + UUID.randomUUID() + material.getOriginalFilename();
                String infoFilePath = homeDirectory + "info/" + "info.txt";

                File file = new File(path);
                File infoFile = new File(infoFilePath);

                if (!infoFile.exists()) {
                    file.createNewFile();
                }
                material.transferTo(file);

                order.setMaterial(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrders(String token) {
        return orderDao.getOrders();
    }
}
