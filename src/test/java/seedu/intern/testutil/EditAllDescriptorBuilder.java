package seedu.intern.testutil;

import seedu.intern.logic.commands.EditAllCommand.EditAllDescriptor;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.ApplicationStatus;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditAllDescriptorBuilder {

    private EditAllDescriptor descriptor;

    public EditAllDescriptorBuilder() {
        descriptor = new EditAllDescriptor();
    }

    public EditAllDescriptorBuilder(EditAllDescriptor descriptor) {
        this.descriptor = new EditAllDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAllDescriptor} with fields containing {@code applicant}'s relevant details
     */
    public EditAllDescriptorBuilder(Applicant applicant) {
        descriptor = new EditAllDescriptor();
        descriptor.setApplicationStatus(applicant.getApplicationStatus());;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditAllDescriptorBuilder withApplicationStatus(String status) {
        descriptor.setApplicationStatus(new ApplicationStatus(status));
        return this;
    }

    public EditAllDescriptor build() {
        return descriptor;
    }


}
