/**
 * Helios is a micro validation library
 *
 * "Helios was the personification of the Sun in Greek mythology. He
 * is the son of the Titan Hyperion and the Titaness Theia (according
 * to Hesiod), also known as Euryphaessa (in Homeric Hymn 31) and
 * brother of the goddesses Selene, the moon, and Eos, the dawn"
 * -- https://en.wikipedia.org/wiki/Helios
 *
 * == Rationale
 *
 * Many Java validation libraries out there are based on
 * annotations. Annotations are ok, but then validation depends on
 * having your data model polluted with them. Moreover, sometimes you
 * may want to have different validation restrictions over the same
 * object in different parts of the application.
 *
 * == Guidelines
 *
 * `Helios` is based on the following ideas:
 *
 * - API simplicity
 * - Small footprint
 * - Validator reusability
 * - Embrace `functional programming`
 * - Documentation
 *
 * @since 0.1.0
 */
package helios;
