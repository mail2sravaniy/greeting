# Service 2 - Greeting Microservice

## 🎯 Overview
Service 2 is a simple greeting service that provides "Hello" message when called by other services (particularly Service 1). It demonstrates a clean, focused microservice with JavaEE log tracing support.

## ✨ Features
- ✅ **Simple Greeting API** - Returns "Hello" wrapped in ResponseEntity
- ✅ **JavaEE Log Tracing** - Supports trace ID propagation from calling services
- ✅ **Health Monitoring** - Built-in health check endpoints
- ✅ **Lightweight Design** - Minimal dependencies, fast startup
- ✅ **Spring Boot 3.2.0** - Modern Spring Boot implementation
- ✅ **Docker Ready** - Complete containerization support

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run
```bash
# Make scripts executable
chmod +x *.sh

# Build the service
./build.sh

# Run the service
./run.sh
```

### Test the Service
```bash
# Run comprehensive tests
./test.sh

# Manual tests
curl http://localhost:8082/api/health
curl http://localhost:8082/api/greeting
```

## 📋 API Endpoints

### Health Check
```http
GET /api/health
```
**Response:** `"Up"`

### Get Greeting
```http
GET /api/greeting
Headers:
  X-Trace-Id: optional-trace-id-from-calling-service
```
**Response:** `"Hello"`

## 🏗️ Integration with Other Services
This service is typically called by Service 1 (Orchestrator) as part of a microservice chain:

1. **Service 1** receives a client request
2. **Service 1** calls **Service 2** `GET /api/greeting` → Gets "Hello"
3. **Service 1** calls Service 3 with user data → Gets "John Doe"
4. **Service 1** combines: "Hello" + "John Doe" = "Hello John Doe"

## 🔍 Configuration
Edit `src/main/resources/application.yml`:
```yaml
server:
  port: 8082
spring:
  application:
    name: service2-greeting
```

## 🐳 Docker Usage
```bash
# Build Docker image
docker build -t service2-greeting .

# Run with Docker Compose
docker-compose up -d
```

## 🔍 Logging
Service 2 supports trace ID propagation for monitoring request flows:
```
# When called with trace ID header
[TraceID: abc-123] Service2 - GET /greeting called
[TraceID: abc-123] Service2 - Returning greeting: Hello
```

## 📊 Architecture Notes
- **Stateless**: No database or persistent storage required
- **Single Responsibility**: Only handles greeting functionality
- **Trace-Aware**: Logs include trace IDs when provided by calling services
- **Resilient**: Simple design reduces failure points
