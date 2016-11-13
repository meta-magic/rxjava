# rxjava

### Functional Reactive Programming - Key Principles.
A responsive, maintainable & Extensible application is the goal.
A responsive application is both scalable (Elastic) and resilient.
Responsiveness is impossible to achieve without both scalability and resilience.
A Message-Driven architecture is the foundation of scalable, resilient, and ultimately responsive systems.
Checkout the Tutorial: Slideshare presentation

### De Coupling
Containment of Failures, Implementation Details, Responsibility
Shared Nothing Architecture, Clear Boundaries
Micro Services: Single Responsibility Principle

### Single Component Pattern
A Component shall do ONLY one thing, But do it in FULL. Single Responsibility Principle By DeMarco : Structured Analysis & System Specification (Yourdon, New York, 1979)

### Let-It-Crash Pattern
Prefer a FULL component restart to complex internal failure handling. Candea & Fox: Crash-Only Software (USENIX HotOS IX, 2003) Popularized by Netflix Chaos Monkey. Erlang Philosophy

### Saga Pattern
Divide long-lived distributed transactions into quick local ones with compensating actions for recovery. Pet Helland: Life Beyond Distributed Transactions CIDR 2007

### Authors and Contributors
Araf Karsh Hamid @arafkarsh Skype, Twitter, Facebook, LinkedIn, G+ : arafkarsh
