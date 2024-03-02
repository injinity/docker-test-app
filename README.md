# Template

[![Quality Gate Status](https://sonar.injinity.org/api/project_badges/measure?project=injinity%3Atemplate&metric=alert_status&token=sqb_3bc93d85f9071c301c57aba83cacb8ca14f2dcc6)](https://sonar.injinity.org/dashboard?id=injinity%3Atemplate)

GitHub template for a standard spring boot service

### Stack
- kotlin
- graalvm native image
- spring framework
- spring boot
- webflux
- actuator

__work in progress!!!__

### Prerequisites

- Platform setup  
[instructions](https://github.com/injinity/platform.git)

### How to use

This is a GitHub template repository which means that to use it you don't commit 
directly to it, instead you select it as the template while creating a new repository.  

1. Open GitHub on the [injinity repository page](https://github.com/orgs/injinity/repositories) and go to __"New repository"__  
  ![image](https://github.com/injinity/template/assets/57630037/e70c692c-c711-403b-9495-a3c16c2b63f7)
2. In the "Repository template" drop-down select __"injinity/template"__  
  ![image](https://github.com/injinity/template/assets/57630037/4956d146-2d01-43df-b123-9a2cbea2a948)
3. Continue with the process as though creating a standard repository

### Setup

- Set a __SUB_ORG__ variable for the repository in GitHub
  - Go to __your__ GitHub repositories settings page
  - Select the __"Secrets and variables"__ drop-down -> __"Actions"__
  - Select the __"Variables"__ tab and click the __"New repository variable"__ button
  - Name it __SUB_ORG__ and set it to your parent project name like _coin-well_
- Set __settings.gradle.kts__ -> __rootProject.name__ to your project name  
- Change the projects package org.injinity.template to org.injinity.\<your-project-name\>

### Next steps

- [Checkout the HELP.md file for more information](https://github.com/injinity/template/blob/main/HELP.md)
- [Setup auth with KeyCloak](https://github.com/) (work in progress)
- [Add state with Postgres](https://github.com/) (work in progress)
- [Add API documentation with Swagger UI](https://github.com/) (work in progress) 

### Related

- [add other spring templates here](https://github.com/) (work in progress)

