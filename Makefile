.PHONY: test run up down load
test:
	./mvnw verify
run:
	./mvnw spring-boot:run
up:
	docker compose up --build
down:
	docker compose down
load:
	docker run --rm -i --network host -e PRODUCT_ID=$(PRODUCT_ID) grafana/k6 run - < load/order-flow.js
