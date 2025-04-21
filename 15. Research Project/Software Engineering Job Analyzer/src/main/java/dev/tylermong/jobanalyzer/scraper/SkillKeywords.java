package dev.tylermong.jobanalyzer.scraper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SkillKeywords
{
    public static final Set<String> VALID_SKILLS = new HashSet<>(Arrays.asList(
        // --- Programming Languages ---
        // Core & General Purpose
        "Java", "Python", "C++", "C#", "JavaScript", "TypeScript", "Go", "Rust", "Swift", "Kotlin", "Ruby", "PHP", "Scala", "Perl", "Objective-C", "Dart", "Groovy", "Lua", "Haskell", "F#", "Clojure", "Elixir", "Erlang", "R", "MATLAB",
        // Low-Level / Systems
        "C", "Assembly Language",
        // Scripting
        "Bash", "Shell Scripting", "PowerShell",
        // Web Specific (Markup/Styling)
        "HTML", "HTML5", "CSS", "CSS3", "Sass", "LESS", "XML", "JSON", "YAML",
        // Domain Specific / Query Languages (some overlap with Databases)
        "SQL", "Cypher", // For Graph DBs
        // Legacy (still relevant in some contexts)
        "COBOL", "Fortran", "Ada",
        // Emerging / WebAssembly
        "WebAssembly", "WASM",

        // --- Platform & Runtimes ---
        "JVM", ".NET", ".NET Core", "Node.js", "Deno", "Bun", "Android Runtime (ART)",

        // --- Frontend Frameworks & Libraries ---
        // Major Frameworks
        "React", "React.js", "Angular", "Vue.js", "Svelte", "Ember.js", "Backbone.js",
        // React Ecosystem
        "Next.js", "Remix", "Gatsby", "Redux", "MobX", "Zustand", "Recoil", "React Router", "React Query", "TanStack Query",
        // Vue Ecosystem
        "Nuxt.js", "Vuex", "Pinia", "Vue Router",
        // Angular Ecosystem
        "NgRx", "RxJS",
        // UI Component Libraries / Design Systems
        "Material UI", "MUI", "Bootstrap", "Tailwind CSS", "Chakra UI", "Ant Design", "Semantic UI", "Foundation", "Bulma",
        // Templating Engines
        "Jinja2", "Thymeleaf", "Handlebars", "Pug", "EJS",
        // Older but still seen
        "jQuery",
        // State Management (General)
        "State Management",
        // Build Tools / Bundlers
        "Webpack", "Vite", "Parcel", "Rollup", "esbuild", "Babel", "SWC",
        // Frontend Concepts
        "Single Page Application", "SPA", "Progressive Web App", "PWA", "Server-Side Rendering", "SSR", "Static Site Generation", "SSG", "Client-Side Rendering", "CSR", "Web Components", "SEO", "Accessibility", "a11y", "Cross-Browser Compatibility", "Responsive Design",

        // --- Backend Frameworks & Libraries ---
        // Java
        "Spring", "Spring Boot", "Spring MVC", "Spring Security", "Spring Data", "Jakarta EE", "Java EE", "JPA", "Hibernate", "MyBatis", "Quarkus", "Micronaut", "Vert.x", "Play Framework", "Akka HTTP",
        // Python
        "Django", "Flask", "FastAPI", "Tornado", "Sanic", "SQLAlchemy", "Celery",
        // JavaScript/TypeScript (Node.js)
        "Express", "Express.js", "Koa", "NestJS", "Hapi", "AdonisJS", "Sails.js", "TypeORM", "Prisma", "Sequelize", "Mongoose",
        // Ruby
        "Ruby on Rails", "Rails", "Sinatra", "ActiveRecord",
        // PHP
        "Laravel", "Symfony", "CakePHP", "CodeIgniter", "Composer", "Eloquent ORM",
        // C# / .NET
        "ASP.NET", "ASP.NET Core", "Entity Framework", "EF Core", "Blazor", "SignalR",
        // Go
        "Gin", "Echo", "Fiber", "Gorilla Mux", "GORM",
        // Rust
        "Actix", "Rocket", "Tokio", "Axum", "Diesel", "SQLx",
        // Scala
        "Play Framework", "Akka HTTP", "Slick", // Re-listed for clarity under Scala
        // Elixir
        "Phoenix", "Ecto",
        // Serverless Frameworks
        "Serverless Framework", "AWS SAM",

        // --- Databases & Data Storage ---
        // Relational (SQL)
        "SQL", "Database Design", "Normalization", "Denormalization", "Indexing", "Query Optimization", "Stored Procedures", "Triggers", "ACID",
        "MySQL", "PostgreSQL", "Postgres", "SQLite", "Microsoft SQL Server", "SQL Server", "Oracle Database", "MariaDB", "Amazon Aurora", "Google Cloud SQL", "Azure SQL Database",
        // NoSQL - Document
        "MongoDB", "Couchbase", "ArangoDB", "Amazon DynamoDB", "DocumentDB", "Azure Cosmos DB", "Google Cloud Firestore",
        // NoSQL - Key-Value
        "Redis", "Memcached", "Riak", "Amazon ElastiCache", "Azure Cache for Redis",
        // NoSQL - Wide-Column / Columnar
        "Cassandra", "HBase", "ScyllaDB", "Google Cloud Bigtable",
        // NoSQL - Graph
        "Neo4j", "ArangoDB", "Amazon Neptune", "Azure Cosmos DB", // (Multi-model)
        // NoSQL - Time Series
        "InfluxDB", "TimescaleDB", "Prometheus", // (Also Monitoring)
        // Search Engines (often used like databases)
        "Elasticsearch", "OpenSearch", "Solr", "Lucene", "Algolia", "Meilisearch",
        // Vector Databases (for AI/ML)
        "Pinecone", "Weaviate", "Milvus", "Chroma DB",
        // Data Warehousing / OLAP
        "Snowflake", "Google BigQuery", "Amazon Redshift", "Azure Synapse Analytics", "ClickHouse", "Data Warehouse", "Data Mart", "ETL", "ELT", "Data Modeling",
        // Object-Relational Mappers (ORMs) / Database Libraries
        "Hibernate", "JPA", // Java (already listed in backend)
        "Entity Framework", "EF Core", "Dapper", // .NET (already listed in backend)
        "SQLAlchemy", // Python (already listed in backend)
        "Sequelize", "TypeORM", "Prisma", "Mongoose", // Node.js (already listed in backend)
        "ActiveRecord", // Ruby (already listed in backend)
        "GORM", // Go (already listed in backend)
        "Diesel", "SQLx", // Rust (already listed in backend)
        "Slick", // Scala (already listed in backend)
        "Ecto", // Elixir (already listed in backend)
        // Database Concepts
        "CAP Theorem", "Database Sharding", "Database Replication", "Caching Strategies", "Connection Pooling",

        // --- Cloud Platforms & Services ---
        // Major Providers
        "AWS", "Amazon Web Services", "Azure", "Microsoft Azure", "Google Cloud", "GCP", "Google Cloud Platform",
        // Other Providers
        "Heroku", "DigitalOcean", "Vercel", "Netlify", "IBM Cloud", "Oracle Cloud Infrastructure", "OCI", "Alibaba Cloud", "Cloudflare",
        // Compute Services
        "EC2", "Azure Virtual Machines", "Google Compute Engine", "GCE", "AWS Lambda", "Azure Functions", "Google Cloud Functions", "AWS Fargate", "Azure Container Instances", "Google Cloud Run", "Serverless", "Virtual Machines", "VMs", "Containers",
        // Storage Services
        "S3", "Azure Blob Storage", "Google Cloud Storage", "GCS", "EBS", "Azure Disk Storage", "Google Persistent Disk", "EFS", "Azure Files", "Google Filestore", "Object Storage", "Block Storage", "File Storage",
        // Database Services (Cloud-Specific)
        "RDS", "Amazon Aurora", "DynamoDB", "Azure SQL Database", "Azure Cosmos DB", "Google Cloud SQL", "Google Cloud Spanner", "Google Cloud Bigtable", "Google Cloud Firestore", // Many already listed under Databases
        // Networking Services
        "VPC", "Azure VNet", "Google VPC Network", "Load Balancing", "ELB", "ALB", "NLB", "Azure Load Balancer", "Google Cloud Load Balancing", "DNS", "Route 53", "Azure DNS", "Google Cloud DNS", "CDN", "CloudFront", "Azure CDN", "Google Cloud CDN", "API Gateway", "AWS API Gateway", "Azure API Management", "Google Cloud API Gateway",
        // Security & Identity
        "IAM", "AWS IAM", "Azure Active Directory", "Azure AD", "Google Cloud IAM", "Security Groups", "Network Security Groups", "NSG", "Firewalls", "WAF", "Secrets Management", "AWS Secrets Manager", "Azure Key Vault", "Google Secret Manager", "HashiCorp Vault",
        // Messaging & Queuing
        "SQS", "Azure Service Bus", "Google Cloud Pub/Sub", "SNS", "Azure Event Grid", "Google Cloud Tasks", "Kafka", "RabbitMQ", "Managed Kafka", "MSK", "Azure Event Hubs",
        // Container Orchestration (Managed)
        "EKS", "AKS", "GKE", // (Also under DevOps)
        // Infrastructure as Code (Cloud-Specific)
        "CloudFormation", "Azure Resource Manager", "ARM Templates", "Google Cloud Deployment Manager",
        // Monitoring & Logging (Cloud-Specific)
        "CloudWatch", "Azure Monitor", "Google Cloud Operations", "Stackdriver",
        // Cloud Concepts
        "IaaS", "PaaS", "SaaS", "FaaS", "Cloud Native", "Multi-cloud", "Hybrid Cloud", "Cloud Security", "Cost Optimization", "Well-Architected Framework",

        // --- DevOps, SRE & Tools ---
        // Version Control
        "Git", "GitHub", "GitLab", "Bitbucket", "SVN", "Subversion", "Mercurial", "Version Control Systems", "VCS", "Gitflow", "Trunk-Based Development",
        // Continuous Integration & Continuous Deployment (CI/CD)
        "CI/CD", "Jenkins", "GitLab CI", "GitHub Actions", "CircleCI", "Travis CI", "Azure DevOps", "Azure Pipelines", "Google Cloud Build", "TeamCity", "Bamboo", "Argo CD", "Flux", "Spinnaker", "Build Automation", "Deployment Strategies", "Blue-Green Deployment", "Canary Release",
        // Containerization
        "Docker", "containerd", "OCI", "Container Images", "Dockerfile",
        // Container Orchestration
        "Kubernetes", "K8s", "Docker Swarm", "Nomad", "OpenShift", "Rancher", "Helm", "Kustomize",
        // Infrastructure as Code (IaC)
        "Terraform", "Pulumi", "Ansible", "Chef", "Puppet", "SaltStack", "CloudFormation", "ARM Templates", "CDK", "AWS CDK", "Azure Bicep", "Crossplane", "Infrastructure as Code", "IaC",
        // Configuration Management
        "Ansible", "Chef", "Puppet", "SaltStack", // Re-listed for clarity
        // Monitoring, Logging & Observability
        "Prometheus", "Grafana", "Datadog", "New Relic", "Dynatrace", "Splunk", "ELK Stack", "Elastic Stack", "Elasticsearch", "Logstash", "Kibana", "Fluentd", "Loki", "Tempo", "Jaeger", "OpenTelemetry", "AppDynamics", "Zabbix", "Nagios", "Monitoring", "Logging", "Tracing", "Alerting", "Metrics", "Observability",
        // Site Reliability Engineering (SRE)
        "SRE", "SLI", "SLO", "SLA", "Error Budgets", "Incident Management", "Postmortems", "Chaos Engineering", "Performance Tuning", "Scalability", "Reliability", "High Availability", "Disaster Recovery",
        // Artifact Repositories
        "Nexus Repository", "JFrog Artifactory", "GitHub Packages", "GitLab Package Registry", "Docker Hub", "AWS ECR", "Azure ACR", "Google Artifact Registry",
        // Build Tools (Language specific, some overlap)
        "Maven", "Gradle", // Java
        "npm", "yarn", "pnpm", // JavaScript
        "pip", "conda", // Python
        "Cargo", // Rust
        "Composer", // PHP
        "RubyGems", "Bundler", // Ruby
        "NuGet", // .NET
        "Go Modules", // Go
        "Make", "CMake", // C/C++
        // Collaboration & Project Management
        "Jira", "Confluence", "Asana", "Trello", "Monday.com", "Slack", "Microsoft Teams", "Agile", "Scrum", "Kanban", // (Also Methodologies)

        // --- Operating Systems ---
        // General
        "Linux", "Windows", "macOS", "Unix",
        // Linux Distributions
        "Ubuntu", "Debian", "CentOS", "RHEL", "Red Hat Enterprise Linux", "Fedora", "Alpine Linux", "Arch Linux",
        // Windows Variants
        "Windows Server",
        // Unix-like
        "BSD", "FreeBSD", "OpenBSD",
        // Concepts
        "Kernel", "Shell", "Command Line Interface", "CLI", "System Administration", "Networking Fundamentals", "File Systems",

        // --- Methodologies & Processes ---
        // Agile
        "Agile", "Agile Principles", "Scrum", "Kanban", "Lean Software Development", "XP", "Extreme Programming", "SAFe", "Scaled Agile Framework", "User Stories", "Story Points", "Velocity", "Retrospectives", "Sprint Planning", "Daily Standup",
        // Development Practices
        "TDD", "Test-Driven Development", "BDD", "Behavior-Driven Development", "DDD", "Domain-Driven Design", "Pair Programming", "Mob Programming", "Code Review", "Refactoring", "Continuous Learning",
        // Traditional
        "Waterfall",
        // Design & Architecture Processes
        "Requirements Gathering", "System Design", "Architecture Design", "Technical Documentation",

        // --- Software Design & Architecture Concepts ---
        // Core Principles
        "OOP", "Object-Oriented Programming", "Encapsulation", "Inheritance", "Polymorphism", "Abstraction", "SOLID Principles", "DRY", "KISS", "YAGNI",
        "Functional Programming", "FP", "Immutability", "Pure Functions", "Higher-Order Functions",
        "Design Patterns", "GoF Patterns", "Creational Patterns", "Structural Patterns", "Behavioral Patterns", "Architectural Patterns",
        // Architecture Styles
        "Monolithic Architecture", "Microservices", "Service-Oriented Architecture", "SOA", "Event-Driven Architecture", "EDA", "Serverless Architecture", "Clean Architecture", "Hexagonal Architecture", "Onion Architecture", "Layered Architecture", "CQRS", "Command Query Responsibility Segregation", "Event Sourcing",
        // API & Communication
        "API", "API Design", "REST", "RESTful APIs", "HATEOAS", "GraphQL", "gRPC", "RPC", "WebSockets", "Server-Sent Events", "SSE", "Message Queues", "Publish/Subscribe", "Pub/Sub", "Idempotency", "API Versioning", "API Security", "OAuth", "OpenID Connect", "JWT", "SAML",
        // Concurrency & Parallelism
        "Concurrency", "Parallelism", "Multithreading", "Asynchronous Programming", "Async/Await", "Promises", "Futures", "Race Conditions", "Deadlocks", "Mutex", "Semaphores", "Actor Model",
        // Data Structures & Algorithms
        "Data Structures", "Arrays", "Linked Lists", "Stacks", "Queues", "Trees", "Graphs", "Hash Tables", "Heaps",
        "Algorithms", "Sorting Algorithms", "Searching Algorithms", "Graph Algorithms", "Dynamic Programming", "Greedy Algorithms", "Complexity Analysis", "Big O Notation",
        // Networking Concepts
        "Networking", "TCP/IP", "HTTP", "HTTPS", "HTTP/2", "HTTP/3", "DNS", "Load Balancing", "Firewalls", "VPN", "Proxies", "Reverse Proxies", "Web Servers", "Nginx", "Apache HTTP Server", "Caching",
        // Security Concepts
        "Cybersecurity", "Application Security", "OWASP Top 10", "Authentication", "Authorization", "Encryption", "Hashing", "TLS/SSL", "Public Key Infrastructure", "PKI", "Security Auditing", "Penetration Testing", "Vulnerability Assessment", "Threat Modeling", "Input Validation", "Secure Coding Practices", "Secrets Management", // (also DevOps)
        // System Design Concepts
        "System Design", "Scalability", "Availability", "Reliability", "Fault Tolerance", "Performance", "Latency", "Throughput", "Consistency Models", // (e.g., Strong, Eventual)

        // --- Mobile Development ---
        // Platforms
        "Android", "iOS",
        // Native Development
        "Java", "Kotlin", // Android (already listed)
        "Swift", "Objective-C", // iOS (already listed)
        "Android SDK", "Jetpack Compose", "XML Layouts", // Android Specific
        "iOS SDK", "Cocoa Touch", "SwiftUI", "UIKit", "XCode", // iOS Specific
        // Cross-Platform Development
        "React Native", "Flutter", "Xamarin", "NativeScript", "Ionic", "Capacitor",
        // Mobile Concepts
        "Mobile UI/UX Design", "Push Notifications", "Location Services", "Mobile Testing", "App Store Submission", "Google Play Store", "Apple App Store", "Mobile Device Management", "MDM",

        // --- Testing & Quality Assurance ---
        // Testing Levels/Types
        "Unit Testing", "Integration Testing", "End-to-End Testing", "E2E Testing", "System Testing", "Acceptance Testing", "User Acceptance Testing", "UAT", "Regression Testing", "Performance Testing", "Load Testing", "Stress Testing", "Security Testing", "Usability Testing", "Accessibility Testing", "Smoke Testing", "Exploratory Testing", "Manual Testing", "Test Automation",
        // Testing Frameworks & Tools (Language Specific)
        "JUnit", "TestNG", "Mockito", "AssertJ", // Java
        "PyTest", "unittest", "Nose2", "unittest.mock", // Python
        "Jest", "Mocha", "Chai", "Sinon", "Cypress", "Playwright", "Testing Library", // JavaScript/TypeScript
        "NUnit", "xUnit.net", "Moq", // .NET
        "RSpec", // Ruby
        "PHPUnit", // PHP
        "Go testing package", "Testify", // Go
        "XCTest", // Swift/Objective-C
        "Espresso", "Robolectric", // Android
        // E2E / UI Automation Tools (General)
        "Selenium", "Cypress", "Playwright", "Appium", "WebDriverIO",
        // Performance Testing Tools
        "JMeter", "K6", "Gatling", "Locust",
        // API Testing Tools
        "Postman", "Insomnia", "RestAssured", // Java
        // Testing Concepts
        "Test Pyramid", "Test Coverage", "Mocking", "Stubbing", "Fakes", "Dependency Injection", "Code Quality", "Static Analysis", "Linters", "Code Metrics", "Mutation Testing",

        // --- Data Science, Machine Learning & AI ---
        // Core Concepts
        "Machine Learning", "ML", "Artificial Intelligence", "AI", "Data Science", "Deep Learning", "Natural Language Processing", "NLP", "Computer Vision", "Statistics", "Probability", "Linear Algebra", "Calculus", "Data Mining", "Predictive Modeling", "Recommender Systems",
        // Libraries & Frameworks
        "Python", // (Dominant language - already listed)
        "NumPy", "Pandas", "SciPy", "Scikit-learn", "TensorFlow", "Keras", "PyTorch", "Jupyter Notebooks", "NLTK", "spaCy", "OpenCV", "Hugging Face Transformers", "XGBoost", "LightGBM", "CatBoost",
        "R", // (Language - already listed)
        "Spark MLlib", // Big Data ML
        // Platforms & Tools
        "AWS SageMaker", "Azure Machine Learning", "Google AI Platform", "Vertex AI", "Databricks", "MLflow", "Kubeflow",
        // Big Data Technologies (Overlap with Data Engineering)
        "Big Data", "Hadoop", "HDFS", "MapReduce", "Apache Spark", "Apache Flink", "Apache Kafka", "Apache Storm", "Data Lakes", "Data Pipelines",

        // --- Data Engineering ---
        // Core Concepts
        "ETL", "ELT", "Data Modeling", "Data Warehousing", "Data Lakes", "Data Pipelines", "Data Governance", "Data Quality", "Batch Processing", "Stream Processing",
        // Tools & Technologies
        "SQL", // (Essential - already listed)
        "Python", // (Common language - already listed)
        "Apache Spark", "Apache Kafka", "Apache Flink", "Apache Airflow", "Luigi", "Prefect", "Dagster", "Hadoop Ecosystem", "HDFS", "Hive", "Pig", "Presto", "Trino", // (Overlap with Big Data/ML)
        "DBT", // Data Build Tool
        "Data Warehouses", "Snowflake", "Redshift", "BigQuery", "Synapse", // (Already listed)
        "Message Queues", "Kafka", "RabbitMQ", "SQS", "Pub/Sub", // (Already listed)

        // --- Game Development ---
        // Engines
        "Unity", "Unreal Engine", "Godot Engine", "CryEngine",
        // Languages
        "C#", "C++", "Blueprints", // (Unreal)
        // Concepts & Libraries
        "Game Physics", "Graphics Programming", "OpenGL", "Vulkan", "DirectX", "Metal", "Shaders", "Game AI", "Networking for Games", "Level Design", "Asset Pipeline",

        // --- Embedded Systems & IoT ---
        // Languages
        "C", "C++", "Assembly", "Python", "MicroPython", "Rust", // (Some already listed)
        // Concepts & Technologies
        "Microcontrollers", "MCU", "Real-Time Operating Systems", "RTOS", "Embedded Linux", "IoT", "Internet of Things", "Sensors", "Actuators", "Firmware", "Hardware Interfacing", "SPI", "I2C", "UART", "MQTT", "CoAP",

        // --- UI/UX Design (Adjacent Skill) ---
        // Concepts
        "User Interface Design", "UI Design", "User Experience Design", "UX Design", "Wireframing", "Prototyping", "User Research", "Usability Testing", "Information Architecture", "Interaction Design", "Design Systems",
        // Tools
        "Figma", "Sketch", "Adobe XD", "InVision", "Zeplin",

        // --- Blockchain & Web3 ---
        // Concepts
        "Blockchain", "Distributed Ledger Technology", "DLT", "Smart Contracts", "Cryptography", "Decentralized Applications", "dApps", "Consensus Mechanisms", "Cryptocurrency", "NFTs",
        // Platforms & Languages
        "Ethereum", "Solidity", "Bitcoin", "Hyperledger Fabric", "Web3.js", "Ethers.js", "Hardhat", "Truffle",

        // --- Soft Skills & Professional Attributes ---
        "Communication", "Verbal Communication", "Written Communication", "Teamwork", "Collaboration", "Problem-Solving", "Critical Thinking", "Analytical Skills", "Adaptability", "Flexibility", "Time Management", "Organization", "Leadership", "Mentoring", "Coaching", "Decision Making", "Creativity", "Curiosity", "Attention to Detail", "Empathy", "Conflict Resolution", "Negotiation", "Presentation Skills", "Technical Writing", "Documentation"
    ));
}