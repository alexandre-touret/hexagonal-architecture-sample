package info.touret.hexagonal_architecture_sample;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

public class ArchitectureTest {

    private JavaClasses classes;

    @BeforeEach
    void setUp() {
        classes = new ClassFileImporter().importPackages("info.touret.hexagonal_architecture_sample.application",
                "info.touret.hexagonal_architecture_sample.domain",
                "info.touret.hexagonal_architecture_sample.infrastructure");
//        classes = new ClassFileImporter().importPackages(HexagonalArchitectureSampleApplication.class.getPackageName()).that(DescribedPredicate.not(  DescribedPredicate.or(JavaClass.Predicates.simpleNameEndingWith("Test"),JavaClass.Predicates.simpleNameEndingWith("Tests"))));
    }

    @Test
    public void should_return_hexagonal_architecture_is_respected() {
        onionArchitecture().domainModels("..info.touret.hexagonal_architecture_sample.domain.riskmanagement.model..")
                .domainServices("..hexagonal_architecture_sample.domain.riskmanagement.service..")
                .applicationServices("..hexagonal_architecture_sample.application..")
                .adapter("persistence", "..hexagonal_architecture_sample.infrastructure.database..").check(classes);

    }
}
