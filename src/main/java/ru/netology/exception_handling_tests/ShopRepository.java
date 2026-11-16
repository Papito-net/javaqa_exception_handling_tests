package ru.netology.exception_handling_tests;

public class ShopRepository {
    private Product[] products = new Product[0];

    // Метод увеличения массива
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    // Метод добавления товара
    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Element with id: " + product.getId() + " already exists");
        }
        products = addToArray(products, product);
    }

    // Метод получения всех товаров
    public Product[] findAll() {
        return products;
    }

    // Метод поиска по id
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    // Метод удаления с проверкой на существование товара с таким id
    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}

