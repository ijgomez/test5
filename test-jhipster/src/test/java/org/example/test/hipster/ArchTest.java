package org.example.test.hipster;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.example.test.hipster");

        noClasses()
            .that()
                .resideInAnyPackage("org.example.test.hipster.service..")
            .or()
                .resideInAnyPackage("org.example.test.hipster.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.example.test.hipster.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
