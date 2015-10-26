package org.kie.demo.bankapp.client;

import javax.enterprise.context.ApplicationScoped;

import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.panels.impl.StaticWorkbenchPanelPresenter;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

@ApplicationScoped
@WorkbenchPerspective(identifier = "MainMortgage", isDefault = true)
public class MortgagePerspective {

    @Perspective
    public PerspectiveDefinition buildPerspective() {
        final PerspectiveDefinition perspective = new PerspectiveDefinitionImpl( StaticWorkbenchPanelPresenter.class.getName() );
        perspective.setName( "MainMortgage" );

        perspective.getRoot().addPart( new PartDefinitionImpl( new DefaultPlaceRequest( "MortgageForm" ) ) );

        return perspective;
    }

}
