package hu.logout.example.divinity.phone.model;

/**
 * Entity that represents a contact
 */
public class Contact {

    private String profilePic;
    private String name;
    private String contactNumber;

    public Contact(String profilePic, String name, String contactNumber) {
        this.profilePic = profilePic;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}