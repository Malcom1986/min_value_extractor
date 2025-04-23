start:
	docker compose up
test:
	./gradlew test
lint:
	./gradlew checkstyleMain checkstyleTest
start-dev:
	./gradlew bootRun
check: test lint
