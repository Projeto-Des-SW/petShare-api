package br.com.ufape.petshare.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ufape.petshare.model.AdoptionAnimal;
import br.com.ufape.petshare.model.Animal;
import br.com.ufape.petshare.model.DonateAnimal;
import br.com.ufape.petshare.model.DonateItem;
import br.com.ufape.petshare.model.Item;
import br.com.ufape.petshare.model.Post;
import br.com.ufape.petshare.model.ReceivedItem;
import br.com.ufape.petshare.model.Request;
import br.com.ufape.petshare.model.User;
import br.com.ufape.petshare.services.AdoptionAnimalServiceInterface;
import br.com.ufape.petshare.services.AnimalServiceInterface;
import br.com.ufape.petshare.services.DonateAnimalServiceInterface;
import br.com.ufape.petshare.services.DonateItemServiceInterface;
import br.com.ufape.petshare.services.ItemServiceInterface;
import br.com.ufape.petshare.services.PostServiceInterface;
import br.com.ufape.petshare.services.ReceivedItemServiceInterface;
import br.com.ufape.petshare.services.RequestServiceInterface;
import br.com.ufape.petshare.services.UserServiceInterface;
import br.com.ufape.petshare.services.exceptions.InvalidStatusException;

@Service
public class PetShare {

	@Autowired
	private UserServiceInterface userService;
	@Autowired
	private AnimalServiceInterface animalService;
	@Autowired
	private ItemServiceInterface itemService;
	@Autowired
	private PostServiceInterface postService;
	@Autowired
	private DonateAnimalServiceInterface donateanimalService;
	@Autowired
	private AdoptionAnimalServiceInterface adoptionanimalService;
	@Autowired
	private DonateItemServiceInterface donateitemService;
	@Autowired
	private ReceivedItemServiceInterface receiveditemService;
	@Autowired
	private RequestServiceInterface requestService;

	/* USER METHODS */
	public User saveUser(User user) {
		return userService.saveUser(user);
	}

	public User findUserById(Long id) {
		return userService.findUserById(id);
	}

	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	public User updateUser(Long id, User userDetails) {
		return userService.updateUser(id, userDetails);
	}

	public void deleteUser(Long id) {
		userService.deleteUser(id);
	}

	public Page<User> findUserPage(PageRequest pageRequest) {
		return userService.findUserPage(pageRequest);
	} /* ANIMAL METHODS */

	public Animal saveAnimal(Animal animal) {
		return animalService.saveAnimal(animal);
	}

	public Animal findAnimalById(Long id) {
		return animalService.findAnimalById(id);
	}

	public List<Animal> getAllAnimals() {
		return animalService.getAllAnimals();
	}

	public Animal updateAnimal(Long id, Animal animalDetails) {
		return animalService.updateAnimal(id, animalDetails);
	}

	public void deleteAnimal(Long id) {
		animalService.deleteAnimal(id);
	}

	public Page<Animal> findAnimalPage(PageRequest pageRequest) {
		return animalService.findAnimalPage(pageRequest);
	} /* ITEM METHODS */

	public Item saveItem(Item item) {
		return itemService.saveItem(item);
	}

	public Item findItemById(Long id) {
		return itemService.findItemById(id);
	}

	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	public Item updateItem(Long id, Item itemDetails) {
		return itemService.updateItem(id, itemDetails);
	}

	public void deleteItem(Long id) {
		itemService.deleteItem(id);
	}

	public Page<Item> findItemPage(PageRequest pageRequest) {
		return itemService.findItemPage(pageRequest);
	}  /* POST METHODS */

	public Post savePost(Post post) {
		return postService.savePost(post);
	}

	public Post findPostById(Long id) {
		return postService.findPostById(id);
	}

	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}

	public Post updatePost(Long id, Post postDetails) {
		return postService.updatePost(id, postDetails);
	}

	public void deletePost(Long id) {
		postService.deletePost(id);
	}

	public Page<Post> findPostPage(PageRequest pageRequest) {
		return postService.findPostPage(pageRequest);
	} /* DONATEANIMAL METHODS */

	public DonateAnimal saveDonateAnimal(DonateAnimal donateanimal) {
		return donateanimalService.saveDonateAnimal(donateanimal);
	}

	public DonateAnimal findDonateAnimalById(Long id) {
		return donateanimalService.findDonateAnimalById(id);
	}
	
	public List<DonateAnimal> findDonateAnimalsByDonorId(Long donorId) {
		return donateanimalService.findDonateAnimalsByDonorId(donorId);
	}

	public List<DonateAnimal> getAllDonateAnimals() {
		return donateanimalService.getAllDonateAnimals();
	}

	public DonateAnimal updateDonateAnimal(Long id, DonateAnimal donateanimalDetails) {
		return donateanimalService.updateDonateAnimal(id, donateanimalDetails);
	}

	public void deleteDonateAnimal(Long id) {
		donateanimalService.deleteDonateAnimal(id);
	}

	public Page<DonateAnimal> findDonateAnimalPage(PageRequest pageRequest) {
		return donateanimalService.findDonateAnimalPage(pageRequest);
	}

	public List<DonateAnimal> getAvailableDonations() {
		return donateanimalService.getAvailableDonations();
	}

	/* ADOPTIONANIMAL METHODS */

	public AdoptionAnimal saveAdoptionAnimal(AdoptionAnimal adoptionanimal) {
		DonateAnimal donateAnimal = findDonateAnimalById(adoptionanimal.getDonateAnimal().getId());
		if (!donateAnimal.getStatus().equals("Disponível"))
			throw new InvalidStatusException("Animal indisponível para adoção");
		donateAnimal.setStatus("Reservada");
		donateAnimal = updateDonateAnimal(donateAnimal.getId(), donateAnimal);
		adoptionanimal.setDonateAnimal(donateAnimal);
		return adoptionanimalService.saveAdoptionAnimal(adoptionanimal);
	}

	public AdoptionAnimal findAdoptionAnimalById(Long id) {
		return adoptionanimalService.findAdoptionAnimalById(id);
	}

	public List<AdoptionAnimal> findAdoptionAnimalsByAdopterId(Long adopterId) {
		findUserById(adopterId);
		return adoptionanimalService.findAdoptionAnimalsByAdopterId(adopterId);
	}

	public List<AdoptionAnimal> findAdoptionAnimalsByDonorId(Long donorId) {
		findUserById(donorId);
		return adoptionanimalService.findAdoptionAnimalsByDonorId(donorId);
	}

	public List<AdoptionAnimal> getAllAdoptionAnimals() {
		return adoptionanimalService.getAllAdoptionAnimals();
	}

	public AdoptionAnimal updateAdoptionAnimal(Long id, AdoptionAnimal adoptionanimalDetails) {
		return adoptionanimalService.updateAdoptionAnimal(id, adoptionanimalDetails);
	}

	private List<String> adoptionsPossibleStatuses = List.of("Em interesse", "Em espera de confirmação de doação",
			"Em espera de confirmação de recebimento", "Finalizada", "Cancelada");

	public void cancelAdoptionAnimal(Long id) {
		AdoptionAnimal adoptionAnimal = findAdoptionAnimalById(id);
		if (adoptionAnimal.getStatus().equals("Finalizada") || adoptionAnimal.getStatus().equals("Cancelada"))
			throw new InvalidStatusException("Cancelamento não é possível, status: " + adoptionAnimal.getStatus());
		adoptionAnimal.setStatus("Cancelada");
		DonateAnimal donateAnimal = adoptionAnimal.getDonateAnimal();
		donateAnimal.setStatus("Disponível");

		updateDonateAnimal(donateAnimal.getId(), donateAnimal);
		updateAdoptionAnimal(id, adoptionAnimal);
	}

	public void deleteAdoptionAnimal(Long id) {
		adoptionanimalService.deleteAdoptionAnimal(id);
	}

	public Page<AdoptionAnimal> findAdoptionAnimalPage(PageRequest pageRequest) {
		return adoptionanimalService.findAdoptionAnimalPage(pageRequest);
	}

	/* RECEIVEDITEM METHODS */

	public DonateItem saveDonateItem(DonateItem donateitem) {
		return donateitemService.saveDonateItem(donateitem);
	}

	public DonateItem findDonateItemById(Long id) {
		return donateitemService.findDonateItemById(id);
	}

	public List<DonateItem> findDonateItemsByDonorId(Long donorId) {
		findUserById(donorId);
		return donateitemService.findDonateItemsByDonorId(donorId);
	}

	public List<DonateItem> getAllDonateItems() {
		return donateitemService.getAllDonateItems();
	}

	public DonateItem updateDonateItem(Long id, DonateItem donateitemDetails) {
		return donateitemService.updateDonateItem(id, donateitemDetails);
	}

	public void deleteDonateItem(Long id) {
		donateitemService.deleteDonateItem(id);
	}

	public Page<DonateItem> findDonateItemPage(PageRequest pageRequest) {
		return donateitemService.findDonateItemPage(pageRequest);
	}

	/* RECEIVEDITEMS METHODS */

	public ReceivedItem saveReceivedItem(ReceivedItem receiveditem) {
		return receiveditemService.saveReceivedItem(receiveditem);
	}

	public ReceivedItem findReceivedItemById(Long id) {
		return receiveditemService.findReceivedItemById(id);
	}
	
	public List<ReceivedItem> findReceivedItemsByReceiverId(Long receiverId) {
		findUserById(receiverId);
		return receiveditemService.findReceivedItemsByReceiverId(receiverId);
	}

	public List<ReceivedItem> findReceivedItemsByDonateId(Long donateId) {
		findDonateItemById(donateId);
		return receiveditemService.findReceivedItemsByDonateId(donateId);
	}

	public List<ReceivedItem> findReceivedItemsByRequestId(Long requestId) {
		findRequestById(requestId);
		return receiveditemService.findReceivedItemsByRequestId(requestId);
	}

	public List<ReceivedItem> findReceivedItemsByDonateDonorId(Long donateDonorId) {
		findUserById(donateDonorId);
		return receiveditemService.findReceivedItemsByDonateDonorId(donateDonorId);
	}

	public List<ReceivedItem> findReceivedItemsByRequestUserId(Long requestUserId) {
		findUserById(requestUserId);
		return receiveditemService.findReceivedItemsByRequestUserId(requestUserId);
	}

	public List<ReceivedItem> getAllReceivedItems() {
		return receiveditemService.getAllReceivedItems();
	}

	public ReceivedItem updateReceivedItem(Long id, ReceivedItem receiveditemDetails) {
		return receiveditemService.updateReceivedItem(id, receiveditemDetails);
	}

	public void deleteReceivedItem(Long id) {
		receiveditemService.deleteReceivedItem(id);
	}

	public Page<ReceivedItem> findReceivedItemPage(PageRequest pageRequest) {
		return receiveditemService.findReceivedItemPage(pageRequest);
	}

	/* REQUEST METHODS */

	public Request saveRequest(Request request) {
		return requestService.saveRequest(request);
	}

	public Request findRequestById(Long id) {
		return requestService.findRequestById(id);
	}
	
	public List<Request> findRequestByUserId(Long userId) {
		return requestService.findRequestByUserId(userId);
	}

	public List<Request> getAllRequests() {
		return requestService.getAllRequests();
	}

	public Request updateRequest(Long id, Request requestDetails) {
		return requestService.updateRequest(id, requestDetails);
	}

	public void deleteRequest(Long id) {
		requestService.deleteRequest(id);
	}

	public Page<Request> findRequestPage(PageRequest pageRequest) {
		return requestService.findRequestPage(pageRequest);
	}
}
