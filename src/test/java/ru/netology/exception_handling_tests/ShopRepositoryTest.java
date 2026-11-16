package ru.netology.exception_handling_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    @Test
    // Тест на успешное удаление существующего товара
    public void shouldSuccessfullyRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Книга", 70);
        Product product2 = new Product(2, "Тетрадь", 15);
        Product product3 = new Product(3, "Ручка", 20);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.removeById(2);

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    // Тест на генерацию исключения при удалении несуществующего товара
    public void shouldThrowNotFoundExceptionIfProductNotFound() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Книга", 70);
        Product product2 = new Product(2, "Тетрадь", 15);

        repo.add(product1);
        repo.add(product2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(8);
        });
    }

    @Test
    public void shouldAddNewProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Книга", 70);
        Product product2 = new Product(2, "Тетрадь", 15);

        repo.add(product1);
        repo.add(product2);

        Product[] expected = {product1, product2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsExceptionWhenAddingDuplicateId() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Книга", 70);
        Product product2 = new Product(1, "Ручка", 20); // Товар с тем же ID = 1

        repo.add(product1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product2);
        });
    }
}
