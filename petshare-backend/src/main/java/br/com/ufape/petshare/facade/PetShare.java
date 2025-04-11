package br.com.ufape.petshare.facade;

import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import br.com.ufape.petshare.model.enums.AdoptionStatus;
import br.com.ufape.petshare.model.enums.DonationStatus;
import br.com.ufape.petshare.model.enums.ItemDonationStatus;
import br.com.ufape.petshare.model.enums.ReceivedItemStatus;
import br.com.ufape.petshare.model.enums.RequestStatus;
import br.com.ufape.petshare.security.AuthUser;
import br.com.ufape.petshare.security.JWTUtils;
import br.com.ufape.petshare.security.UserDetailsServiceImpl;
import br.com.ufape.petshare.services.AdoptionAnimalServiceInterface;
import br.com.ufape.petshare.services.AnimalServiceInterface;
import br.com.ufape.petshare.services.DonateAnimalServiceInterface;
import br.com.ufape.petshare.services.DonateItemServiceInterface;
import br.com.ufape.petshare.services.ItemServiceInterface;
import br.com.ufape.petshare.services.PostServiceInterface;
import br.com.ufape.petshare.services.ReceivedItemServiceInterface;
import br.com.ufape.petshare.services.RequestServiceInterface;
import br.com.ufape.petshare.services.UserServiceInterface;
import br.com.ufape.petshare.services.exceptions.AuthenticationException;
import br.com.ufape.petshare.services.exceptions.AuthorizationException;
import br.com.ufape.petshare.services.exceptions.InvalidReceivedItemException;
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
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JWTUtils jwtUtils;
	
	/* AUTH METHODS */
	
	public User findLoggedUser() {
		User logged = findUserByEmail(userDetailsServiceImpl.authenticated().getEmail());
		if (logged == null)
			throw new AuthenticationException("Usuário não autenticado");
		return logged;
	}
	
	public String generateLoginToken(AuthUser usuario) {
		return jwtUtils.generateLoginToken(usuario);
	}

	public String recoverEmailByToken(String token) {
		return jwtUtils.recoverEmailByToken(token);
	}
	
	public void updatePassword(String password, String newPassword) {
		User logged = findLoggedUser();
		if (!passwordEncoder.matches(password, logged.getPassword()))
			throw new AuthorizationException("Senha incorreta");
		userService.updateUserPasswordByEmail(logged.getEmail(), passwordEncoder.encode(newPassword));
		
	}

	/* USER METHODS */
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.saveUser(user);
	}

	public User findUserById(Long id) {
		return userService.findUserById(id);
	}
	
	public User findUserByEmail(String email) {
		return userService.findUserByEmail(email);
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
	}
	
	/* ANIMAL METHODS */

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
	} /* POST METHODS */

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

	public List<DonateAnimal> getAvailableAnimalDonations() {
		return donateanimalService.getAvailableDonations();
	}

	/* ADOPTIONANIMAL METHODS */

	public AdoptionAnimal saveAdoptionAnimal(AdoptionAnimal adoptionanimal) {
		DonateAnimal donateAnimal = findDonateAnimalById(adoptionanimal.getDonateAnimal().getId());
		if (donateAnimal.getStatus() != DonationStatus.DISPONIVEL)
			throw new InvalidStatusException("Animal indisponível para adoção");
		
		donateAnimal.setStatus(DonationStatus.EM_INTERESSE);
		donateAnimal = updateDonateAnimal(donateAnimal.getId(), donateAnimal);
		adoptionanimal.setDonateAnimal(donateAnimal);
		return adoptionanimalService.saveAdoptionAnimal(adoptionanimal);
	}

	public void cancelAdoptionAnimal(Long id) {
		AdoptionAnimal adoptionAnimal = findAdoptionAnimalById(id);
		if (EnumSet.of(
			AdoptionStatus.FINALIZADA,
			AdoptionStatus.CANCELADA,
			AdoptionStatus.RECUSADA
		).contains(adoptionAnimal.getStatus()))
			throw new InvalidStatusException("Cancelamento não é possível, status: " + adoptionAnimal.getStatus());
		adoptionAnimal.setStatus(AdoptionStatus.CANCELADA);
		DonateAnimal donateAnimal = adoptionAnimal.getDonateAnimal();
		donateAnimal.setStatus(DonationStatus.DISPONIVEL);

		updateDonateAnimal(donateAnimal.getId(), donateAnimal);
		updateAdoptionAnimal(id, adoptionAnimal);
	}

	public void refuseAdoptionAnimal(Long id) {
		AdoptionAnimal adoptionAnimal = findAdoptionAnimalById(id);
		if (adoptionAnimal.getStatus() != AdoptionStatus.EM_INTERESSE){
			throw new InvalidStatusException("Recusa não é possível, status: " + adoptionAnimal.getStatus());
		}
		adoptionAnimal.setStatus(AdoptionStatus.RECUSADA);
		DonateAnimal donateAnimal = adoptionAnimal.getDonateAnimal();
		donateAnimal.setStatus(DonationStatus.DISPONIVEL);

		updateDonateAnimal(donateAnimal.getId(), donateAnimal);
		updateAdoptionAnimal(id, adoptionAnimal);
	}

	public void confirmAdoptionAnimal(Long id) {
		AdoptionAnimal adoptionAnimal = findAdoptionAnimalById(id);
		if (adoptionAnimal.getStatus() != AdoptionStatus.EM_INTERESSE)
			throw new InvalidStatusException("Confirmação não é possível, status: " + adoptionAnimal.getStatus());
		adoptionAnimal.setStatus(AdoptionStatus.EM_ESPERA_CONFIRMACAO_RECEBIMENTO);
		DonateAnimal donateAnimal = adoptionAnimal.getDonateAnimal();
		donateAnimal.setStatus(DonationStatus.EM_ESPERA_CONFIRMACAO_RECEBIMENTO);
		updateAdoptionAnimal(id, adoptionAnimal);
	}

	public void confirmReceiptAdoptionAnimal(Long id) {
		AdoptionAnimal adoptionAnimal = findAdoptionAnimalById(id);
		if (adoptionAnimal.getStatus() != AdoptionStatus.EM_ESPERA_CONFIRMACAO_RECEBIMENTO)
			throw new InvalidStatusException("Confirmação não é possível, status: " + adoptionAnimal.getStatus());
		adoptionAnimal.setStatus(AdoptionStatus.FINALIZADA);

		DonateAnimal donateAnimal = adoptionAnimal.getDonateAnimal();
		donateAnimal.setStatus(DonationStatus.ADOTADO);
		updateDonateAnimal(donateAnimal.getId(), donateAnimal);

		updateAdoptionAnimal(id, adoptionAnimal);
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
	
	public List<AdoptionAnimal> findAdoptionAnimalsByDonateId(Long donateId) {
		findDonateAnimalById(donateId);
		return adoptionanimalService.findAdoptionAnimalsByDonateId(donateId);
	}

	public List<AdoptionAnimal> getAllAdoptionAnimals() {
		return adoptionanimalService.getAllAdoptionAnimals();
	}

	public AdoptionAnimal updateAdoptionAnimal(Long id, AdoptionAnimal adoptionanimalDetails) {
		return adoptionanimalService.updateAdoptionAnimal(id, adoptionanimalDetails);
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

	public List<DonateItem> getAvailableItemsDonations() {
		return donateitemService.getAvailableDonations();
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

	public ReceivedItem saveReceivedItem(ReceivedItem receivedItem) {
		Request request = receivedItem.getRequest();
		DonateItem donateItem = receivedItem.getDonateItem();
		if(request == null && donateItem == null)
			throw new InvalidReceivedItemException("O recebimento precisa de uma requisição ou doação vinculada");
		
		if(donateItem.getId() != null) {
			donateItem = findDonateItemById(donateItem.getId());
			receivedItem.setQuantity(donateItem.getQuantity());
			donateItem.setStatus(ItemDonationStatus.RESERVADO);
			donateItem = updateDonateItem(donateItem.getId(), donateItem);
			receivedItem.setDonateItem(donateItem);
		} else {
			request = findRequestById(receivedItem.getRequest().getId());
			donateItem.setItem(request.getItem());
			receivedItem.setDonateItem(saveDonateItem(donateItem));
			System.out.println(receivedItem.getDonateItem().getItem());
		}
		return receiveditemService.saveReceivedItem(receivedItem);
	}
	
	public void cancelReceivedItem(Long id) {
		ReceivedItem receivedItem = findReceivedItemById(id);
		if (EnumSet.of(
			ItemDonationStatus.RECEBIDO,
			ItemDonationStatus.CANCELADO,
			ItemDonationStatus.INDISPONIVEL
		).contains(receivedItem.getStatus()))
			throw new InvalidStatusException("Cancelamento não é possível, status: " + receivedItem.getStatus());
		receivedItem.setStatus(ReceivedItemStatus.CANCELADO);
		
		DonateItem donateItem = receivedItem.getDonateItem();
		
		if(donateItem != null) { 
			donateItem.setStatus(ItemDonationStatus.DISPONIVEL);
			donateItem = updateDonateItem(donateItem.getId(), donateItem);
			receivedItem.setDonateItem(donateItem);
		}

		updateReceivedItem(id, receivedItem);
	}

	public void refuseReceivedItem(Long id) {
		ReceivedItem receivedItem = findReceivedItemById(id);
		if (receivedItem.getStatus() !=  ReceivedItemStatus.EM_INTERESSE)
			throw new InvalidStatusException("Recusa não é possível, status: " + receivedItem.getStatus());
		receivedItem.setStatus(ReceivedItemStatus.RECUSADO);
		
		DonateItem donateItem = receivedItem.getDonateItem();
		
		if(donateItem != null) { 
			donateItem.setStatus(ItemDonationStatus.DISPONIVEL);
			donateItem = updateDonateItem(donateItem.getId(), donateItem);
			receivedItem.setDonateItem(donateItem);
		}

		updateReceivedItem(id, receivedItem);
	}
	
	public void confirmReceivedItem(Long id) {
		ReceivedItem receivedItem = findReceivedItemById(id);
		if (!receivedItem.getStatus().equals(ReceivedItemStatus.EM_INTERESSE))
			throw new InvalidStatusException("Confirmação não é possível, status: " + receivedItem.getStatus());
		receivedItem.setStatus(ReceivedItemStatus.ESPERANDO_CONFIRMACAO_RECEBIMENTO);
		DonateItem donateItem = receivedItem.getDonateItem();
		donateItem.setStatus(ItemDonationStatus.EM_ESPERA_CONFIRMACAO_RECEBIMENTO);
		updateReceivedItem(id, receivedItem);
	}
	
	public void confirmReceiptReceivedItem(Long id) {
		ReceivedItem receivedItem = findReceivedItemById(id);
		if (!receivedItem.getStatus().equals(ReceivedItemStatus.ESPERANDO_CONFIRMACAO_RECEBIMENTO))
			throw new InvalidStatusException("Confirmação não é possível, status: " + receivedItem.getStatus());
		receivedItem.setStatus(ReceivedItemStatus.FINALIZADO);

		DonateItem donateItem = receivedItem.getDonateItem();
		
		if(donateItem != null) { 
			donateItem.setStatus(ItemDonationStatus.DISPONIVEL);
			donateItem = updateDonateItem(donateItem.getId(), donateItem);
			receivedItem.setDonateItem(donateItem);
		}
		
		Request request = receivedItem.getRequest();
		
		if(request != null) {
			request.addReceivedQuantity(receivedItem.getQuantity());
			if(request.getQuantity() >= request.getReceivedQuantity())
				request.setStatus(RequestStatus.FINALIZADA);
			request = updateRequest(request.getId(), request);
			receivedItem.setRequest(request);
		}

		updateReceivedItem(id, receivedItem);
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
		findUserById(userId);
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

	public List<Request> getOpenRequests() {
		return requestService.getOpenRequests();
	}

}
