package info.touret.hexagonal_architecture_sample;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = {ArchitectureTest.APPLICATION_ROOT_PACKAGE,
    ArchitectureTest.DOMAIN_ROOT_PACKAGE,
    ArchitectureTest.INFRASTRUCTURE_ROOT_PACKAGE},
                importOptions = DoNotIncludeTests.class)
public class ArchitectureTest {

    public static final String DOMAIN_ROOT_PACKAGE = "info.touret.hexagonal_architecture_sample.domain";
    public static final String APPLICATION_ROOT_PACKAGE = "info.touret.hexagonal_architecture_sample.application";
    public static final String INFRASTRUCTURE_ROOT_PACKAGE = "info.touret.hexagonal_architecture_sample.infrastructure";

    @ArchTest
    public static final ArchRule should_return_hexagonal_architecture_is_respected =
            onionArchitecture().domainModels("..domain.(**).model..")
                    .domainServices("..domain.(**).service..")
                    .applicationServices("..application..")
                    .adapter("persistence", "..infrastructure.database..");

    @ArchTest
    public static final ArchRule should_return_the_domain_doesnt_depend_on_spring= noClasses().that().resideInAPackage(ArchitectureTest.DOMAIN_ROOT_PACKAGE+"..").should().accessClassesThat().resideInAPackage("org.springframework..");

    @ArchTest
    public static final ArchRule should_return_the_domain_has_no_dependency= classes().that().resideInAPackage(ArchitectureTest.DOMAIN_ROOT_PACKAGE+"..").should().onlyAccessClassesThat().resideInAnyPackage(ArchitectureTest.DOMAIN_ROOT_PACKAGE+"..","java..");

    @ArchTest
    public static final ArchRule should_return_the_application_layer_doesnt_depends_on_the_infrastructure= noClasses().that().resideInAPackage(ArchitectureTest.APPLICATION_ROOT_PACKAGE+"..").should().accessClassesThat().resideInAPackage(ArchitectureTest.INFRASTRUCTURE_ROOT_PACKAGE+"..");

    @ArchTest
    public static final ArchRule should_return_the_infrastructure_layer_doesnt_depends_on_the_application= noClasses().that().resideInAPackage(ArchitectureTest.INFRASTRUCTURE_ROOT_PACKAGE+"..").should().accessClassesThat().resideInAPackage(ArchitectureTest.APPLICATION_ROOT_PACKAGE+"..");


}

