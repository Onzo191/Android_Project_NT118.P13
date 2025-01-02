package com.example.androidproject.features.checkout.usecase;

import com.example.androidproject.R;
import com.example.androidproject.core.errors.Failure;
import com.example.androidproject.core.utils.Either;
import com.example.androidproject.features.brand.data.model.BrandModel;
import com.example.androidproject.features.cart.data.entity.ProductsOnCart;
import com.example.androidproject.features.checkout.data.model.CheckoutModel;
import com.example.androidproject.features.checkout.data.repository.CheckoutRepository;
import com.example.androidproject.features.checkout.data.repository.CheckoutRepositoryImpl;
import com.example.androidproject.features.order.data.ProductDataForOrderModel;
import com.example.androidproject.features.product.data.model.ProductModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CheckoutUseCase {
    private CheckoutRepository checkoutRepository = new CheckoutRepositoryImpl(FirebaseFirestore.getInstance());

    public CompletableFuture<Either<Failure, String>> addCheckout(CheckoutModel checkoutModel, long quantity) {
        return checkoutRepository.addCheckoutRepository(checkoutModel, quantity)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getCheckoutList(String userId) {
        return checkoutRepository.getCheckoutList(userId)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getLatestCheckouts(int limit) {
        return checkoutRepository.getLatestCheckouts(limit)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, Integer>> getNumberCheckoutToday() {
        return checkoutRepository.getNumberCheckoutToday()
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getCheckoutListByStatus(String status) {
        return checkoutRepository.getCheckoutListByStatus(status)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getCheckoutListByStatusAndUserId(String status, String userId) {
        return checkoutRepository.getCheckoutListByStatusAndUserId(status, userId)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getCheckoutListByUserId(String userId) {
        return checkoutRepository.getCheckoutListByUserId(userId)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getCheckoutListByFilter(String field, String filter, String userId) {
        return checkoutRepository.getCheckoutListByFilter(field, filter, userId)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getCheckoutsThisMonth() {
        return checkoutRepository.getCheckoutsThisMonth()
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getCheckoutsToday() {
        return checkoutRepository.getCheckoutsToday()
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, Boolean>> updateStatus(String checkoutId, String status) {
        return checkoutRepository.updateStatus(checkoutId, status)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getAllCheckouts() {
        return checkoutRepository.getAllCheckouts()
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, List<CheckoutModel>>> getAllCheckoutsByStatus(String status) {
        return checkoutRepository.getALlCheckoutsByStatus(status)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }

    public CompletableFuture<Either<Failure, CheckoutModel>> getCheckoutById(String checkoutId) {
        return checkoutRepository.getCheckoutById(checkoutId)
                .thenApply(r -> {
                    if(r.isRight()) {
                        return Either.right(r.getRight());
                    } else {
                        return Either.left(r.getLeft());
                    }
                });
    }
}
