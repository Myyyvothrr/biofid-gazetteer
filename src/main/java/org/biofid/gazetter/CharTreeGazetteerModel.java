package org.biofid.gazetter;

import org.biofid.gazetter.Models.SkipGramGazetteerModel;
import org.biofid.gazetter.TreeSearch.CharTreeNode;

import java.io.IOException;
import java.util.stream.Stream;

public class CharTreeGazetteerModel extends SkipGramGazetteerModel {

    public final CharTreeNode tree;

    /**
     * Create 1-skip-n-grams from each taxon in a file from a given list of files.
     *
     * @param aSourceLocations An array of UTF-8 file locations containing a list of one taxon and any number of URIs (comma
     *                         or space separated) per line.
     * @param bUseLowercase    If true, use lower cased skip-grams.
     * @param sLanguage        The language to be used as locale for lower casing.
     * @param dMinLength       The minimum skip-gram length. All skip-grams (and taxa) with a length lower than this will be
     *                         omitted.
     * @param bAllSkips        If true, get all m-skip-n-grams of length n > 2.
     * @param bSplitHyphen     If true, taxon tokens will be split at hyphens.
     * @throws IOException
     */
    public CharTreeGazetteerModel(String[] aSourceLocations, Boolean bUseLowercase, String sLanguage, double dMinLength, boolean bAllSkips, boolean bSplitHyphen) throws IOException {
        super(aSourceLocations, bUseLowercase, sLanguage, dMinLength, bAllSkips, bSplitHyphen);
        long startTime = System.currentTimeMillis();

        System.out.printf("%s: Building tree..\n", this.getClass().getSimpleName());

        tree = new CharTreeNode();
        for (String skipGram : skipGramSet) {
            tree.insert(bUseLowercase ? skipGram.toLowerCase() : skipGram);
        }

        System.out.printf(
                "%s: Finished building tree with %d nodes from %d skip-grams in %dms.\n",
                this.getClass().getSimpleName(), tree.size(), skipGramSet.size(),
                System.currentTimeMillis() - startTime
        );
    }

    public

    @Deprecated
    Stream<String> stream() {
        throw new UnsupportedOperationException();
    }
}
