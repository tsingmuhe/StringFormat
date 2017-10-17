# StringFormat

For example,

	StringFormat.format("Hi {}.", "there");
   
will return the string "Hi there.".

	StringFormat.format("Set {1,2,3} is not equal to {}.", "1,2");
   
will return the string "Set {1,2,3} is not equal to 1,2.".

	StringFormat.format("Set \\{} is not equal to {}.", "1,2");
   
will return the string "Set {} is not equal to 1,2.".

	StringFormat.format("Hi {}.", new Object[]{new String[]{"1", "2", "3"}});

will return the string "Hi [1, 2, 3].".

**reference:**

	org.slf4j.helpers.MessageFormatter

The formatting conventions are different than those of MessageFormat which ships with the Java platform. This is justified by the fact that SLF4J's implementation is 10 times faster than that of MessageFormat. This local performance difference is both measurable and significant in the larger context of the complete logging processing chain.