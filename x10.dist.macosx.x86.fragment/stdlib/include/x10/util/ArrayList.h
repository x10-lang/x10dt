#ifndef __X10_UTIL_ARRAYLIST_H
#define __X10_UTIL_ARRAYLIST_H

#include <x10rt.h>


#define X10_UTIL_ABSTRACTCOLLECTION_H_NODEPS
#include <x10/util/AbstractCollection.h>
#undef X10_UTIL_ABSTRACTCOLLECTION_H_NODEPS
#define X10_UTIL_LIST_H_NODEPS
#include <x10/util/List.h>
#undef X10_UTIL_LIST_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(T)> class GrowableRail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Container;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class List;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class ListIterator;
} } 
namespace x10 { namespace util { 
template<class FMGL(S)> class ArrayList__It;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Comparable;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Collection;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class ArrayList;
template <> class ArrayList<void>;
template<class FMGL(T)> class ArrayList : public x10::util::AbstractCollection<FMGL(T)>
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[8];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::util::Container<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::ArrayList<FMGL(T)> > _itable_1;
    
    static typename x10::lang::Iterable<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> > _itable_2;
    
    static typename x10::util::Collection<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> > _itable_3;
    
    static typename x10::util::List<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> > _itable_4;
    
    static typename x10::util::Indexed<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> > _itable_5;
    
    static typename x10::lang::Settable<x10_int, FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> > _itable_6;
    
    void _instance_init();
    
    x10aux::ref<x10::util::GrowableRail<FMGL(T) > > FMGL(a);
    
    virtual x10_boolean contains(FMGL(T) v);
    virtual x10aux::ref<x10::util::Container<FMGL(T)> > clone();
    virtual x10_boolean add(FMGL(T) v);
    virtual x10_boolean remove(FMGL(T) v);
    virtual void addBefore(x10_int i, FMGL(T) v);
    virtual FMGL(T) __set(FMGL(T) v, x10_int i);
    virtual FMGL(T) set(FMGL(T) v, x10_int i);
    virtual FMGL(T) removeAt(x10_int i);
    virtual FMGL(T) __apply(x10_int i);
    virtual FMGL(T) get(x10_int i);
    virtual x10_int size();
    virtual x10_boolean isEmpty();
    virtual x10aux::ref<x10::array::Array<FMGL(T)> > toArray();
    virtual x10aux::ref<x10::lang::Rail<FMGL(T) > > toRail();
    void _constructor();
    
    static x10aux::ref<x10::util::ArrayList<FMGL(T)> > _make();
    
    void _constructor(x10_int size);
    
    static x10aux::ref<x10::util::ArrayList<FMGL(T)> > _make(x10_int size);
    
    virtual FMGL(T) removeFirst();
    virtual FMGL(T) removeLast();
    virtual FMGL(T) getFirst();
    virtual FMGL(T) getLast();
    virtual x10aux::ref<x10::util::List<x10_int> > indices();
    virtual x10aux::ref<x10::util::List<FMGL(T)> > subList(x10_int begin,
                                                           x10_int end);
    virtual x10_int indexOf(FMGL(T) v);
    virtual x10_int indexOf(x10_int index, FMGL(T) v);
    virtual x10_int lastIndexOf(FMGL(T) v);
    virtual x10_int lastIndexOf(x10_int index, FMGL(T) v);
    virtual x10aux::ref<x10::lang::Rail<FMGL(T) > > moveSectionToRail(
      x10_int i,
      x10_int j);
    virtual x10aux::ref<x10::lang::Iterator<FMGL(T)> > iterator();
    virtual x10aux::ref<x10::util::ListIterator<FMGL(T)> > iteratorFrom(
      x10_int i);
    virtual void reverse();
    virtual void sort();
    virtual void sort(x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> > cmp);
    void qsort(x10aux::ref<x10::util::GrowableRail<FMGL(T) > > a,
               x10_int lo,
               x10_int hi,
               x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> > cmp);
    void exch(x10aux::ref<x10::util::GrowableRail<FMGL(T) > > a, x10_int i,
              x10_int j);
    virtual x10aux::ref<x10::util::ArrayList<FMGL(T)> > x10__util__ArrayList____x10__util__ArrayList__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class ArrayList<void> : public x10::util::AbstractCollection<void>
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    template<class FMGL(T)>
    static x10aux::ref<x10::util::ArrayList<FMGL(T)> >
      make(
      x10aux::ref<x10::util::Container<FMGL(T)> > c);
    
    
};

} } 
#endif // X10_UTIL_ARRAYLIST_H

namespace x10 { namespace util { 
template<class FMGL(T)>
class ArrayList;
} } 

#ifndef X10_UTIL_ARRAYLIST_H_NODEPS
#define X10_UTIL_ARRAYLIST_H_NODEPS
#include <x10/util/AbstractCollection.h>
#include <x10/util/List.h>
#include <x10/util/GrowableRail.h>
#include <x10/util/Container.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#include <x10/array/Array.h>
#include <x10/lang/Rail.h>
#include <x10/util/List.h>
#include <x10/lang/Rail.h>
#include <x10/util/ListIterator.h>
#include <x10/util/ArrayList__It.h>
#include <x10/lang/Comparable.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/util/Collection.h>
#include <x10/lang/Iterator.h>
#ifndef X10_UTIL_ARRAYLIST__CLOSURE__0_CLOSURE
#define X10_UTIL_ARRAYLIST__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_2.h>
template<class FMGL(T)> class x10_util_ArrayList__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int>::template itable <x10_util_ArrayList__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int __apply(FMGL(T) x, FMGL(T) y) {
        
        //#line 217 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
        return x10::lang::Comparable<FMGL(T) >::compareTo(x10aux::class_cast<x10aux::ref<x10::lang::Comparable<FMGL(T) > > >(x), y);
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_ArrayList__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_util_ArrayList__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_ArrayList__closure__0<FMGL(T) > >(storage));
        x10aux::ref<x10_util_ArrayList__closure__0<FMGL(T) > > this_ = new (storage) x10_util_ArrayList__closure__0<FMGL(T) >();
        return this_;
    }
    
    x10_util_ArrayList__closure__0() { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10:217";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int>::template itable <x10_util_ArrayList__closure__0<FMGL(T) > >x10_util_ArrayList__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_ArrayList__closure__0<FMGL(T) >::__apply, &x10_util_ArrayList__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_ArrayList__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> >, &x10_util_ArrayList__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_ArrayList__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_ArrayList__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_ARRAYLIST__CLOSURE__0_CLOSURE
#ifndef X10_UTIL_ARRAYLIST_H_GENERICS
#define X10_UTIL_ARRAYLIST_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::util::ArrayList<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::ArrayList<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::ArrayList<FMGL(T)> >(), 0, sizeof(x10::util::ArrayList<FMGL(T)>))) x10::util::ArrayList<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_ARRAYLIST_H_GENERICS
#ifndef X10_UTIL_ARRAYLIST_H_IMPLEMENTATION
#define X10_UTIL_ARRAYLIST_H_IMPLEMENTATION
#include <x10/util/ArrayList.h>


template<class FMGL(T)> typename x10::util::Container<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> >  x10::util::ArrayList<FMGL(T)>::_itable_0(&x10::util::ArrayList<FMGL(T)>::clone, &x10::util::ArrayList<FMGL(T)>::contains, &x10::util::ArrayList<FMGL(T)>::containsAll, &x10::util::ArrayList<FMGL(T)>::equals, &x10::util::ArrayList<FMGL(T)>::hashCode, &x10::util::ArrayList<FMGL(T)>::isEmpty, &x10::util::ArrayList<FMGL(T)>::iterator, &x10::util::ArrayList<FMGL(T)>::size, &x10::util::ArrayList<FMGL(T)>::toRail, &x10::util::ArrayList<FMGL(T)>::toString, &x10::util::ArrayList<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::util::ArrayList<FMGL(T)> >  x10::util::ArrayList<FMGL(T)>::_itable_1(&x10::util::ArrayList<FMGL(T)>::equals, &x10::util::ArrayList<FMGL(T)>::hashCode, &x10::util::ArrayList<FMGL(T)>::toString, &x10::util::ArrayList<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::Iterable<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> >  x10::util::ArrayList<FMGL(T)>::_itable_2(&x10::util::ArrayList<FMGL(T)>::equals, &x10::util::ArrayList<FMGL(T)>::hashCode, &x10::util::ArrayList<FMGL(T)>::iterator, &x10::util::ArrayList<FMGL(T)>::toString, &x10::util::ArrayList<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::Collection<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> >  x10::util::ArrayList<FMGL(T)>::_itable_3(&x10::util::ArrayList<FMGL(T)>::add, &x10::util::ArrayList<FMGL(T)>::addAll, &x10::util::ArrayList<FMGL(T)>::addAllWhere, &x10::util::ArrayList<FMGL(T)>::clear, &x10::util::ArrayList<FMGL(T)>::clone, &x10::util::ArrayList<FMGL(T)>::contains, &x10::util::ArrayList<FMGL(T)>::containsAll, &x10::util::ArrayList<FMGL(T)>::equals, &x10::util::ArrayList<FMGL(T)>::hashCode, &x10::util::ArrayList<FMGL(T)>::isEmpty, &x10::util::ArrayList<FMGL(T)>::iterator, &x10::util::ArrayList<FMGL(T)>::remove, &x10::util::ArrayList<FMGL(T)>::removeAll, &x10::util::ArrayList<FMGL(T)>::removeAllWhere, &x10::util::ArrayList<FMGL(T)>::retainAll, &x10::util::ArrayList<FMGL(T)>::size, &x10::util::ArrayList<FMGL(T)>::toRail, &x10::util::ArrayList<FMGL(T)>::toString, &x10::util::ArrayList<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::List<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> >  x10::util::ArrayList<FMGL(T)>::_itable_4(&x10::util::ArrayList<FMGL(T)>::add, &x10::util::ArrayList<FMGL(T)>::addAll, &x10::util::ArrayList<FMGL(T)>::addAllWhere, &x10::util::ArrayList<FMGL(T)>::addBefore, &x10::util::ArrayList<FMGL(T)>::clear, &x10::util::ArrayList<FMGL(T)>::clone, &x10::util::ArrayList<FMGL(T)>::contains, &x10::util::ArrayList<FMGL(T)>::containsAll, &x10::util::ArrayList<FMGL(T)>::equals, &x10::util::ArrayList<FMGL(T)>::getFirst, &x10::util::ArrayList<FMGL(T)>::getLast, &x10::util::ArrayList<FMGL(T)>::hashCode, &x10::util::ArrayList<FMGL(T)>::indexOf, &x10::util::ArrayList<FMGL(T)>::indexOf, &x10::util::ArrayList<FMGL(T)>::indices, &x10::util::ArrayList<FMGL(T)>::isEmpty, &x10::util::ArrayList<FMGL(T)>::iterator, &x10::util::ArrayList<FMGL(T)>::iteratorFrom, &x10::util::ArrayList<FMGL(T)>::lastIndexOf, &x10::util::ArrayList<FMGL(T)>::lastIndexOf, &x10::util::ArrayList<FMGL(T)>::__apply, &x10::util::ArrayList<FMGL(T)>::__set, &x10::util::ArrayList<FMGL(T)>::remove, &x10::util::ArrayList<FMGL(T)>::removeAll, &x10::util::ArrayList<FMGL(T)>::removeAllWhere, &x10::util::ArrayList<FMGL(T)>::removeAt, &x10::util::ArrayList<FMGL(T)>::removeFirst, &x10::util::ArrayList<FMGL(T)>::removeLast, &x10::util::ArrayList<FMGL(T)>::retainAll, &x10::util::ArrayList<FMGL(T)>::reverse, &x10::util::ArrayList<FMGL(T)>::size, &x10::util::ArrayList<FMGL(T)>::sort, &x10::util::ArrayList<FMGL(T)>::sort, &x10::util::ArrayList<FMGL(T)>::subList, &x10::util::ArrayList<FMGL(T)>::toRail, &x10::util::ArrayList<FMGL(T)>::toString, &x10::util::ArrayList<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::Indexed<FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> >  x10::util::ArrayList<FMGL(T)>::_itable_5(&x10::util::ArrayList<FMGL(T)>::clone, &x10::util::ArrayList<FMGL(T)>::contains, &x10::util::ArrayList<FMGL(T)>::containsAll, &x10::util::ArrayList<FMGL(T)>::equals, &x10::util::ArrayList<FMGL(T)>::hashCode, &x10::util::ArrayList<FMGL(T)>::isEmpty, &x10::util::ArrayList<FMGL(T)>::iterator, &x10::util::ArrayList<FMGL(T)>::__apply, &x10::util::ArrayList<FMGL(T)>::size, &x10::util::ArrayList<FMGL(T)>::toRail, &x10::util::ArrayList<FMGL(T)>::toString, &x10::util::ArrayList<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::Settable<x10_int, FMGL(T)>::template itable<x10::util::ArrayList<FMGL(T)> >  x10::util::ArrayList<FMGL(T)>::_itable_6(&x10::util::ArrayList<FMGL(T)>::equals, &x10::util::ArrayList<FMGL(T)>::hashCode, &x10::util::ArrayList<FMGL(T)>::__set, &x10::util::ArrayList<FMGL(T)>::toString, &x10::util::ArrayList<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::util::ArrayList<FMGL(T)>::_itables[8] = {x10aux::itable_entry(&x10aux::getRTT<x10::util::Container<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >, &_itable_2), x10aux::itable_entry(&x10aux::getRTT<x10::util::Collection<FMGL(T)> >, &_itable_3), x10aux::itable_entry(&x10aux::getRTT<x10::util::List<FMGL(T)> >, &_itable_4), x10aux::itable_entry(&x10aux::getRTT<x10::util::Indexed<FMGL(T)> >, &_itable_5), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Settable<x10_int, FMGL(T)> >, &_itable_6), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::ArrayList<FMGL(T)> >())};
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::ArrayList<FMGL(T)>");
    
}


//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10FieldDecl_c

//#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c

//#line 24 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::ArrayList<FMGL(T)>::contains(
  FMGL(T) v) {
    
    //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < ((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                          FMGL(a))->length())); 
                                                                //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
                                                                i =
                                                                  ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10If_c
            if ((x10aux::struct_equals(v,
                                       X10_NULL))
                  ? (x10_boolean)((x10aux::struct_equals((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                            FMGL(a))->__apply(i),
                                                         X10_NULL)))
                  : (x10_boolean)(x10aux::equals(v,x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                                                                                 FMGL(a))->__apply(i)))))
            {
                
                //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
                return true;
                
            }
            
        }
    }
    
    //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return false;
    
}

//#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::Container<FMGL(T)> >
  x10::util::ArrayList<FMGL(T)>::clone(
  ) {
    
    //#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::ArrayList<FMGL(T)> > a =
      x10::util::ArrayList<FMGL(T)>::_make();
    
    //#line 35 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    a->addAll(x10aux::class_cast_unchecked<x10aux::ref<x10::util::Container<FMGL(T)> > >(((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)));
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return a;
    
}

//#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::ArrayList<FMGL(T)>::add(
  FMGL(T) v) {
    
    //#line 40 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
       FMGL(a))->add(v);
    
    //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return true;
    
}

//#line 44 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::ArrayList<FMGL(T)>::remove(
  FMGL(T) v) {
    
    //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < ((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                          FMGL(a))->length()));
             
             //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10If_c
            if ((x10aux::struct_equals(v,
                                       X10_NULL))
                  ? (x10_boolean)((x10aux::struct_equals((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                            FMGL(a))->__apply(i),
                                                         X10_NULL)))
                  : (x10_boolean)(x10aux::equals(v,x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                                                                                 FMGL(a))->__apply(i)))))
            {
                
                //#line 47 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
                ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->removeAt(
                  i);
                
                //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
                return true;
                
            }
            
        }
    }
    
    //#line 51 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return false;
    
}

//#line 54 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::addBefore(
  x10_int i,
  FMGL(T) v) {
    
    //#line 55 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
       FMGL(a))->add(v);
    
    //#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int j;
        for (
             //#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             j = ((x10_int) ((i) + (((x10_int)1))));
             ((j) < ((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                        FMGL(a))->length()));
             
             //#line 56 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
             j =
               ((x10_int) ((j) + (((x10_int)1)))))
        {
            
            //#line 57 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
            (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
               FMGL(a))->__set((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                  FMGL(a))->__apply(((x10_int) ((j) - (((x10_int)1))))), j);
        }
    }
    
    //#line 59 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
       FMGL(a))->__set(v, i);
}

//#line 62 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::__set(
  FMGL(T) v,
  x10_int i) {
    
    //#line 62 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->set(
             v,
             i);
    
}

//#line 64 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::set(
  FMGL(T) v,
  x10_int i) {
    
    //#line 65 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
       FMGL(a))->__set(v, i);
    
    //#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return v;
    
}

//#line 69 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::removeAt(
  x10_int i) {
    
    //#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    FMGL(T) v = (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                   FMGL(a))->__apply(i);
    
    //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int j;
        for (
             //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             j = ((x10_int) ((i) + (((x10_int)1))));
             ((j) < ((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                        FMGL(a))->length()));
             
             //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
             j =
               ((x10_int) ((j) + (((x10_int)1)))))
        {
            
            //#line 72 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
            (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
               FMGL(a))->__set((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                  FMGL(a))->__apply(j), ((x10_int) ((j) - (((x10_int)1)))));
        }
    }
    
    //#line 74 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
       FMGL(a))->removeLast();
    
    //#line 75 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return v;
    
}

//#line 78 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::__apply(
  x10_int i) {
    
    //#line 78 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
              FMGL(a))->__apply(i);
    
}

//#line 80 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::get(
  x10_int i) {
    
    //#line 80 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
              FMGL(a))->__apply(i);
    
}

//#line 82 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::ArrayList<FMGL(T)>::size(
  ) {
    
    //#line 82 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
              FMGL(a))->length();
    
}

//#line 84 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::ArrayList<FMGL(T)>::isEmpty(
  ) {
    
    //#line 84 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return (x10aux::struct_equals(((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->size(),
                                  ((x10_int)0)));
    
}

//#line 87 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::array::Array<FMGL(T)> >
  x10::util::ArrayList<FMGL(T)>::toArray(
  ) {
    
    //#line 87 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
              FMGL(a))->toArray();
    
}

//#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Rail<FMGL(T) > >
  x10::util::ArrayList<FMGL(T)>::toRail(
  ) {
    
    //#line 88 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
              FMGL(a))->toRail();
    
}

//#line 90 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::_constructor(
                          ) {
    this->::x10::util::AbstractCollection<FMGL(T)>::_constructor();
    {
     
    }
    
    //#line 91 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
      FMGL(a) = x10::util::GrowableRail<FMGL(T) >::_make();
    
}
template<class FMGL(T)> x10aux::ref<x10::util::ArrayList<FMGL(T)> > x10::util::ArrayList<FMGL(T)>::_make(
                          ) {
    x10aux::ref<x10::util::ArrayList<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::ArrayList<FMGL(T)> >(), 0, sizeof(x10::util::ArrayList<FMGL(T)>))) x10::util::ArrayList<FMGL(T)>();
    this_->_constructor();
    return this_;
}



//#line 94 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::_constructor(
                          x10_int size) {
    this->::x10::util::AbstractCollection<FMGL(T)>::_constructor();
    {
     
    }
    
    //#line 95 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
      FMGL(a) = x10::util::GrowableRail<FMGL(T) >::_make(size);
    
}
template<class FMGL(T)> x10aux::ref<x10::util::ArrayList<FMGL(T)> > x10::util::ArrayList<FMGL(T)>::_make(
                          x10_int size) {
    x10aux::ref<x10::util::ArrayList<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::ArrayList<FMGL(T)> >(), 0, sizeof(x10::util::ArrayList<FMGL(T)>))) x10::util::ArrayList<FMGL(T)>();
    this_->_constructor(size);
    return this_;
}



//#line 98 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::removeFirst(
  ) {
    
    //#line 98 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->removeAt(
             ((x10_int)0));
    
}

//#line 99 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::removeLast(
  ) {
    
    //#line 99 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->removeAt(
             ((x10_int) (((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                             FMGL(a))->length()) - (((x10_int)1)))));
    
}

//#line 100 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::getFirst(
  ) {
    
    //#line 100 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->get(
             ((x10_int)0));
    
}

//#line 101 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::ArrayList<FMGL(T)>::getLast(
  ) {
    
    //#line 101 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->get(
             ((x10_int) (((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                             FMGL(a))->length()) - (((x10_int)1)))));
    
}

//#line 103 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::List<x10_int> >
  x10::util::ArrayList<FMGL(T)>::indices(
  ) {
    
    //#line 104 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::ArrayList<x10_int> > l =
      x10::util::ArrayList<x10_int>::_make();
    
    //#line 105 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 105 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < ((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                          FMGL(a))->length()));
             
             //#line 105 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 106 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
            l->add(
              i);
        }
    }
    
    //#line 108 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::util::List<x10_int> > >(l);
    
}

//#line 111 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::List<FMGL(T)> >
  x10::util::ArrayList<FMGL(T)>::subList(
  x10_int begin,
  x10_int end) {
    
    //#line 112 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::ArrayList<FMGL(T)> > l =
      x10::util::ArrayList<FMGL(T)>::_make();
    
    //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             i = begin; ((i) < ((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                   FMGL(a))->length())) &&
                        ((i) < (end)); 
                                       //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
                                       i =
                                         ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 114 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
            l->add(
              (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                 FMGL(a))->__apply(i));
        }
    }
    
    //#line 116 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::util::List<FMGL(T)> > >(l);
    
}

//#line 119 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::ArrayList<FMGL(T)>::indexOf(
  FMGL(T) v) {
    
    //#line 120 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->indexOf(
             ((x10_int)0),
             v);
    
}

//#line 123 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::ArrayList<FMGL(T)>::indexOf(
  x10_int index,
  FMGL(T) v) {
    
    //#line 124 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 124 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             i = index; ((i) < ((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                   FMGL(a))->length()));
             
             //#line 124 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 125 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10If_c
            if ((x10aux::struct_equals(v,
                                       X10_NULL))
                  ? (x10_boolean)((x10aux::struct_equals((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                            FMGL(a))->__apply(i),
                                                         X10_NULL)))
                  : (x10_boolean)(x10aux::equals(v,x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                                                                                 FMGL(a))->__apply(i)))))
            {
                
                //#line 126 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
                return i;
                
            }
            
        }
    }
    
    //#line 128 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10_int)-1);
    
}

//#line 131 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::ArrayList<FMGL(T)>::lastIndexOf(
  FMGL(T) v) {
    
    //#line 132 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->lastIndexOf(
             ((x10_int) (((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                             FMGL(a))->length()) - (((x10_int)1)))),
             v);
    
}

//#line 135 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::ArrayList<FMGL(T)>::lastIndexOf(
  x10_int index,
  FMGL(T) v) {
    
    //#line 136 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 136 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             i = index; ((i) >= (((x10_int)0)));
             
             //#line 136 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) - (((x10_int)1)))))
        {
            
            //#line 137 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10If_c
            if ((x10aux::struct_equals(v,
                                       X10_NULL))
                  ? (x10_boolean)((x10aux::struct_equals((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                            FMGL(a))->__apply(i),
                                                         X10_NULL)))
                  : (x10_boolean)(x10aux::equals(v,x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                                                                                                                 FMGL(a))->__apply(i)))))
            {
                
                //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
                return i;
                
            }
            
        }
    }
    
    //#line 140 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10_int)-1);
    
}

//#line 143 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Rail<FMGL(T) > >
  x10::util::ArrayList<FMGL(T)>::moveSectionToRail(
  x10_int i,
  x10_int j) {
    
    //#line 143 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
              FMGL(a))->moveSectionToRail(i, j);
    
}

//#line 201 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Iterator<FMGL(T)> >
  x10::util::ArrayList<FMGL(T)>::iterator(
  ) {
    
    //#line 202 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::util::ListIterator<FMGL(T)> > >(x10::util::ArrayList__It<FMGL(T)>::_make(((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)));
    
}

//#line 205 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::ListIterator<FMGL(T)> >
  x10::util::ArrayList<FMGL(T)>::iteratorFrom(
  x10_int i) {
    
    //#line 206 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::util::ListIterator<FMGL(T)> > >(x10::util::ArrayList__It<FMGL(T)>::_make(((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this),
                                                                                                                                  i));
    
}

//#line 209 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::reverse(
  ) {
    
    //#line 210 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    x10_int length = (((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                        FMGL(a))->length();
    
    //#line 211 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 211 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (((x10_int) ((length) / x10aux::zeroCheck(((x10_int)2))))));
             
             //#line 211 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 212 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
            ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->exch(
              ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                FMGL(a),
              i,
              ((x10_int) ((((x10_int) ((length) - (((x10_int)1))))) - (i))));
        }
    }
    
}

//#line 217 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::sort(
  ) {
    {
        
        //#line 217 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->sort(
          x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> > >(x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> >(x10aux::ref<x10_util_ArrayList__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> >(sizeof(x10_util_ArrayList__closure__0<FMGL(T)>)))x10_util_ArrayList__closure__0<FMGL(T)>()))));
        
        //#line 217 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
        return;
    }
}

//#line 218 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::sort(
  x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> > cmp) {
    {
        
        //#line 218 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->qsort(
          ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
            FMGL(a),
          ((x10_int)0),
          ((x10_int) (((((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->
                          FMGL(a))->length()) - (((x10_int)1)))),
          cmp);
        
        //#line 218 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
        return;
    }
}

//#line 229 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::qsort(
  x10aux::ref<x10::util::GrowableRail<FMGL(T) > > a,
  x10_int lo,
  x10_int hi,
  x10aux::ref<x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int> > cmp) {
    
    //#line 230 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10If_c
    if (((hi) <= (lo))) {
        
        //#line 230 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
        return;
    }
    
    //#line 231 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    x10_int l = ((x10_int) ((lo) - (((x10_int)1))));
    
    //#line 232 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    x10_int h = hi;
    
    //#line 233 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10While_c
    while (true) {
        
        //#line 234 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10While_c
        while (((x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int>::__apply(x10aux::nullCheck(cmp), 
                 (a)->__apply(l =
                   ((x10_int) ((l) + (((x10_int)1))))),
                 (a)->__apply(hi))) < (((x10_int)0))))
        {
            
            //#line 234 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Empty_c
            ;
        }
        
        //#line 235 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10While_c
        while (((x10::lang::Fun_0_2<FMGL(T), FMGL(T), x10_int>::__apply(x10aux::nullCheck(cmp), 
                 (a)->__apply(hi),
                 (a)->__apply(h =
                   ((x10_int) ((h) - (((x10_int)1))))))) < (((x10_int)0))) &&
               ((h) > (lo))) {
            
            //#line 235 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Empty_c
            ;
        }
        
        //#line 236 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10If_c
        if (((l) >= (h))) {
            
            //#line 236 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Branch_c
            break;
        }
        
        //#line 237 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->exch(
          a,
          l,
          h);
    }
    
    //#line 239 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->exch(
      a,
      l,
      hi);
    
    //#line 240 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->qsort(
      a,
      lo,
      ((x10_int) ((l) - (((x10_int)1)))),
      cmp);
    
    //#line 241 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this)->qsort(
      a,
      ((x10_int) ((l) + (((x10_int)1)))),
      hi,
      cmp);
}

//#line 244 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::exch(
  x10aux::ref<x10::util::GrowableRail<FMGL(T) > > a,
  x10_int i,
  x10_int j) {
    
    //#line 245 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    FMGL(T) temp = (a)->__apply(i);
    
    //#line 246 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    (a)->__set((a)->__apply(j), i);
    
    //#line 247 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    (a)->__set(temp, j);
}

//#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::ArrayList<FMGL(T)> >
  x10::util::ArrayList<FMGL(T)>::x10__util__ArrayList____x10__util__ArrayList__this(
  ) {
    
    //#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::ArrayList<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::util::ArrayList<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::ArrayList<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::util::AbstractCollection<FMGL(T)>::_serialize_body(buf);
    buf.write(this->FMGL(a));
    
}

template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::util::AbstractCollection<FMGL(T)>::_deserialize_body(buf);
    FMGL(a) = buf.read<x10aux::ref<x10::util::GrowableRail<FMGL(T) > > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::ArrayList<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::ArrayList<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::ArrayList<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::util::AbstractCollection<FMGL(T)> >(), x10aux::getRTT<x10::util::List<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.ArrayList";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
template<class FMGL(T)> x10aux::ref<x10::util::ArrayList<FMGL(T)> >
  x10::util::ArrayList<void>::make(x10aux::ref<x10::util::Container<FMGL(T)> > c)
{
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::ArrayList<FMGL(T)> > a =
      x10::util::ArrayList<FMGL(T)>::_make();
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": polyglot.ast.Eval_c
    a->addAll(
      c);
    
    //#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/ArrayList.x10": x10.ast.X10Return_c
    return a;
    
}
#endif // X10_UTIL_ARRAYLIST_H_IMPLEMENTATION
#endif // __X10_UTIL_ARRAYLIST_H_NODEPS
