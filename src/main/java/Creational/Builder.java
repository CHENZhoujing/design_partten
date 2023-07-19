package Creational;

/*
 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 */

public class Builder {
    public static void main(String[] args) {
        ValueDistributeDTO dto = ValueDistributeDTO.builder()
                .businessType("type")
                .business("business")
                .designateDataSource("dataSource")
                .build();
        System.out.println(dto.toString());
    }
}

class ValueDistributeDTO {
    private String businessType;
    private String business;
    private String designateDataSource;

    public ValueDistributeDTO() {
    }

    public ValueDistributeDTO(String businessType, String business, String designateDataSource) {
        this.businessType = businessType;
        this.business = business;
        this.designateDataSource = designateDataSource;
    }

    public static ValueDistributeDTOBuilder builder() {
        return new ValueDistributeDTOBuilder();
    }

    @Override
    public String toString() {
        return "ValueDistributeDTO{" +
                "businessType='" + businessType + '\'' +
                ", business='" + business + '\'' +
                ", designateDataSource='" + designateDataSource + '\'' +
                '}';
    }

    public static class ValueDistributeDTOBuilder {
        private String businessType;
        private String business;
        private String designateDataSource;

        ValueDistributeDTOBuilder() {
        }

        public ValueDistributeDTOBuilder businessType(String businessType) {
            this.businessType = businessType;
            return this;
        }

        public ValueDistributeDTOBuilder business(String business) {
            this.business = business;
            return this;
        }

        public ValueDistributeDTOBuilder designateDataSource(String designateDataSource) {
            this.designateDataSource = designateDataSource;
            return this;
        }

        public ValueDistributeDTO build() {
            return new ValueDistributeDTO(this.businessType, this.business, this.designateDataSource);
        }
    }
}

