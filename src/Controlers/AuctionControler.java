package Controlers;

import Databases.AuctionDataBase;
import interfaceWithUsers.AuctionInterface;
import models.Auction;
import models.User;
import views.AuctionView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

//OSobny pakiet na przetrymywanie danych , controler - >
// dodac klase user controler met login i ona odwołuje sie do userdatabase

public class AuctionControler implements Serializable {





    public void addAuction(AuctionDataBase dataBase, Auction auction) {
        dataBase.addAuction(auction);
        AuctionView auctionView = new AuctionView();
        auctionView.showComunicatWhenAuctionAdded();
    }

    public void getAuctions(AuctionDataBase auctionDataBase) {
        CategoryControler categoryControler = new CategoryControler();
        AuctionInterface auctionInterface = new AuctionInterface();
        Integer catIdToPrintAuctions = auctionInterface.choseCategoryId("Write id of category to which would you like to show auctions (Write 0 to see all) : ", categoryControler.getSetOfCategoryId());
        AuctionView.printAllAuctions(filtrListToCategory(auctionDataBase.getListOfAllAuction(), catIdToPrintAuctions));
    }

    public Integer choseCategoryForAddedAuctions() {
        CategoryControler categoryControler = new CategoryControler();
        AuctionInterface auctionInterface = new AuctionInterface();
        categoryControler.showAllCategories();
        return auctionInterface.choseCategoryId("Chose id of category to which you would like to add auction: ", categoryControler.getSetOfCategoriesAvileableToAdd());
    }

    public static ArrayList<Auction> filtrListToCategory(ArrayList<Auction> listofAllAuction, Integer categoryId) {
        if (categoryId == 1) {
            return listofAllAuction.stream()
                    .filter(a -> (a.getCategoryId().equals(1) || a.getCategoryId().equals(5) || a.getCategoryId().equals(6)))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else if (categoryId == 5) {
            return listofAllAuction.stream()
                    .filter(a -> (a.getCategoryId().equals(5)))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else if (categoryId == 6) {
            return listofAllAuction.stream()
                    .filter(a -> (a.getCategoryId().equals(6)))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else if (categoryId == 2) {
            return listofAllAuction.stream()
                    .filter(a -> (a.getCategoryId().equals(2) || a.getCategoryId().equals(3) || a.getCategoryId().equals(4)))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else if (categoryId == 3) {
            return listofAllAuction.stream()
                    .filter(a -> (a.getCategoryId().equals(3)))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else if (categoryId == 4) {
            return listofAllAuction.stream()
                    .filter(a -> (a.getCategoryId().equals(4)))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return listofAllAuction;
    }


    public void removeAuction(AuctionDataBase auctionDataBase, Auction auction) {
        AuctionView auctionView = new AuctionView();
        try {
            auctionDataBase.removeAuction(auction);
            auctionView.printingMessages("Auction added");
        } catch (NullPointerException e) {
            auctionView.printingMessages("Auction adding Failed !! ");
        }

    }
}
