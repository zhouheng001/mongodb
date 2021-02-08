import guru.springframework.SpringBootMongodbApplication;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMongodbApplication.class)
public class test {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        List<Product> all = mongoTemplate.findAll(Product.class);
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("description").regex("^.*描述奇异.*$").and("price").lte(17).gte(11 );
        query.addCriteria(criteria);
        query.skip(1).limit(2);

        List<Product> products = mongoTemplate.find(query, Product.class);

        for (Product product : products) {
            System.out.println(product);
        }
    }

}

@Document
class Product {
    @Id
    private ObjectId _id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
