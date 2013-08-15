package br.com.caelum.brutal.validators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.brutal.dao.TestCase;
import br.com.caelum.brutal.factory.MessageFactory;
import br.com.caelum.brutal.model.Tag;
import br.com.caelum.brutal.model.User;
import br.com.caelum.vraptor4.Validator;
import br.com.caelum.vraptor4.core.Localization;
import br.com.caelum.vraptor4.util.test.MockLocalization;
import br.com.caelum.vraptor4.util.test.MockValidator;

public class TagsValidatorTest extends TestCase {
	
	private MessageFactory messageFactory;
	private Localization localization;
	private Validator validator;
	private TagsValidator tagsValidator;
	private Tag java;
	private Tag rails;
	private Tag ruby;
	
	@Before
	public void setup() {
		localization = new MockLocalization();
		messageFactory = new MessageFactory(localization);
		validator = new MockValidator();
		tagsValidator = new TagsValidator(validator, messageFactory);
		User user = user("any", "any@brutal.com");
		java = new Tag("java", "", user);
		ruby = new Tag("ruby", "", user);
		rails = new Tag("rails", "", user);
	}
	
	@Test
	public void should_not_validate_tags_not_found() throws Exception {
		List<String> wanted = Arrays.asList("java", "blabla", "blablablabla");
		List<Tag> found = Arrays.asList(java);
		assertFalse(tagsValidator.validate(found, wanted));
		assertEquals(2, validator.getErrors().size());
	}
	
	@Test
	public void should_validate_all_tags_found() throws Exception {
		List<String> wanted = Arrays.asList("java", "ruby", "rails");
		List<Tag> found = Arrays.asList(rails, java, ruby);
		assertTrue(tagsValidator.validate(found, wanted));
	}

}
