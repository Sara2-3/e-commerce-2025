@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ... other fields ...

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stock> stocks = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    // ... other methods ...

    public void addStock(Stock stock) {
        stocks.add(stock);
        stock.setProduct(this);
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
        stock.setProduct(null);
    }
} 